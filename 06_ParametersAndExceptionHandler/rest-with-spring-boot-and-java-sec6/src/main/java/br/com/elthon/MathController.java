package br.com.elthon;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.elthon.exceptions.UnsupportedMathOperationException;

@RestController
public class MathController {
	
	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();
	
	@RequestMapping(value = "/sum/{numberOne}/{numberTwo}", method=RequestMethod.GET) // RequestMapping vai mapear uma requisição para um método
	public Double sum(
			@PathVariable (value = "numberOne") String numberOne,
			@PathVariable (value = "numberTwo") String numberTwo
			) throws Exception {
	
		if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value.");
		}
		
		return convertToDouble(numberOne) + convertToDouble(numberTwo);
	}
	
	@RequestMapping(value = "/subtraction/{numberOne}/{numberTwo}", method=RequestMethod.GET) // RequestMapping vai mapear uma requisição para um método
	public Double subtraction(
			@PathVariable (value = "numberOne") String numberOne,
			@PathVariable (value = "numberTwo") String numberTwo
			) throws Exception {
	
		if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value.");
		}
		
		return convertToDouble(numberOne) - convertToDouble(numberTwo);
	}

	@RequestMapping(value = "/multiplication/{numberOne}/{numberTwo}", method=RequestMethod.GET) // RequestMapping vai mapear uma requisição para um método
	public Double multiplication(
			@PathVariable (value = "numberOne") String numberOne,
			@PathVariable (value = "numberTwo") String numberTwo
			) throws Exception {
	
		if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value.");
		}
		
		return convertToDouble(numberOne) * convertToDouble(numberTwo);
	}
	
	@RequestMapping(value = "/division/{numberOne}/{numberTwo}", method=RequestMethod.GET) // RequestMapping vai mapear uma requisição para um método
	public Double division(
			@PathVariable (value = "numberOne") String numberOne,
			@PathVariable (value = "numberTwo") String numberTwo
			) throws Exception {
	
		if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value.");
		}
		
		return convertToDouble(numberOne) / convertToDouble(numberTwo);
	}
	
	@RequestMapping(value = "/average/{numberOne}/{numberTwo}", method=RequestMethod.GET) // RequestMapping vai mapear uma requisição para um método
	public Double average(
			@PathVariable (value = "numberOne") String numberOne,
			@PathVariable (value = "numberTwo") String numberTwo
			) throws Exception {
	
		if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value.");
		}
		
		return (convertToDouble(numberOne) + convertToDouble(numberTwo)) / 2;
	}
	
	@RequestMapping(value = "/sqrt/{number}", method=RequestMethod.GET) // RequestMapping vai mapear uma requisição para um método
	public Double sqrt(
			@PathVariable (value = "number") String number
			) throws Exception {
	
		if (!isNumeric(number)) {
			throw new UnsupportedMathOperationException("Please set a numeric value.");
		}
		
		return Math.sqrt(convertToDouble(number));
	}
	
	private Double convertToDouble(String strNumber) {
		if (strNumber == null) return 0D;
		// BR 10,25 ES 10.25
		String number = strNumber.replaceAll(",", ".");
		return Double.parseDouble(number);
	}

	private boolean isNumeric(String strNumber) {
		if (strNumber == null) return false;
		String number = strNumber.replaceAll(",", ".");
		return number.matches("[-+]?[0-9]*\\.?[0-9]+");
	}

}
