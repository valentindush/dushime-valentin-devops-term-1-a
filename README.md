# DevOps Exam Term One
## MathController API
A well implemented `MathController` API with POST method called
doMath using dto DoMathRequest
```java
@Data
@AllArgsConstructor
public class DoMathRequestDTO {
    private double operand1;
    private double operand2;
    private String operation;


    public double getOperand1() {
        return operand1;
    }

    public void setOperand1(double operand1) {
        this.operand1 = operand1;
    }

    public double getOperand2() {
        return operand2;
    }

    public void setOperand2(double operand2) {
        this.operand2 = operand2;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
```
```java
@RestController
@RequestMapping("/api/v1/do_math")
public class MathController {
    private final MathOperatorImpl mathOperatorImpl;
    public MathController(MathOperatorImpl mathOperatorImpl) {
        this.mathOperatorImpl = mathOperatorImpl;
    }

    @PostMapping
    public ResponseEntity<ApiResponse> create(@RequestBody DoMathRequestDTO dto) throws InvalidOperationException {
        return ResponseEntity.ok(ApiResponse.success(mathOperatorImpl.doMath(dto.getOperand1(), dto.getOperand2(), dto.getOperation())));
    }
}
```
## MathOperator
Interface `MathOperator` and its Implementation MathOperatorImpl, the
controller should call the `MathOperatorImpl`’s method doMath to do math
```java
public interface IMathOperator {
    double doMath(double operand1, double operand2, String operation) throws InvalidOperationException;
}
```
```java
public class MathOperatorImpl implements IMathOperator {
    @Override
    public double doMath(double operand1, double operand2, String operation) throws InvalidOperationException {
        if ("/".equals(operation) && operand2 == (double) 0) {
            throw new InvalidOperationException("Cannot divide by 0");
        }

        switch (operation) {
            case "*":
                return operand1 * operand2;
            case "/":
                return operand1 / operand2;
            case "+":
                return operand1 + operand2;
            case "-":
                return operand1 - operand2;
            default:
                throw new RuntimeException("Unknown operation");
        }
    }
}
```
## UNIT TEST
Unit test for `MathOperatorImpl`
```java
class MathOperatorServiceTest {

    private IMathOperator mathOperator;

    @BeforeEach
    void setUp() {
        mathOperator = new MathOperatorImpl();
    }

    @Test
    void testDoMathMultiplication() throws InvalidOperationException {
        double result = mathOperator.doMath(2, 3, "*");
        assertEquals(6, result, 0.001);
    }

    @Test
    void testDoMathDivision() throws InvalidOperationException {
        double result = mathOperator.doMath(6, 2, "/");
        assertEquals(3, result, 0.001);
    }

    @Test
    void testDoMathAddition() throws InvalidOperationException {
        double result = mathOperator.doMath(5, 3, "+");
        assertEquals(8, result, 0.001);
    }

    @Test
    void testDoMathSubtraction() throws InvalidOperationException {
        double result = mathOperator.doMath(8, 3, "-");
        assertEquals(5, result, 0.001);
    }

    @Test
    void testDoMathUnknownOperation() {
        assertThrows(RuntimeException.class, () -> mathOperator.doMath(2, 3, "unknown"));
    }

    @Test
    void testDoMathDivisionByZero() {
        assertThrows(InvalidOperationException.class, () -> mathOperator.doMath(5, 0, "/"));
    }
}
```
## INTEGRATION TEST
Integration test for `MathController` and `MathOperatorImpl`
```java
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MathControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void doMathOperation_Success(){
        DoMathRequestDTO dto = new DoMathRequestDTO(2, 5, "+");

        ResponseEntity<ApiResponse> response = this.restTemplate.postForEntity("/api/v1/do_math",dto,ApiResponse.class);

        assertEquals(200, response.getStatusCode().value());
    }
}
```
## E2E TEST
E2E Test using `RestAssured` to simulate browser interactions
```java
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DushimevalentinTermoneAApplicationTests {
	@LocalServerPort
	private int port;

	@Test
	public void testDoMathOperation_E2E() {
		// Set the base URI for RestAssured
		RestAssured.baseURI = "http://localhost:" + port;

		// Given
		double operand1 = 2;
		double operand2 = 5;
		String operation = "+";

		// When
		given()
                .contentType(ContentType.JSON)
                .body("{\"operand1\": " + operand1 + ", \"operand2\": " + operand2 + ", \"operation\": \"" + operation + "\"}")
                .when()
                .post("/api/v1/do_math")
                .then()
                .statusCode(200)
                .body("data", equalTo(7.0f));
	}
}
```