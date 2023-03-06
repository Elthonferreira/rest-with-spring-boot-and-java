package br.com.elthon.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.elthon.converters.NumberConverter;
import br.com.elthon.exceptions.UnsupportedMathOperationException;
import br.com.elthon.math.SimpleMath;

@RestController
public class MathController {
	
	private SimpleMath math = new SimpleMath();
	
	@RequestMapping(value = "/sum/{numberOne}/{numberTwo}", method=RequestMethod.GET) // RequestMapping vai mapear uma requisição para um método
	public Double sum(
			@PathVariable (value = "numberOne") String numberOne,
			@PathVariable (value = "numberTwo") String numberTwo
			) throws Exception {
	
		if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value.");
		}
		
		return math.sum(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
	}
	
	@RequestMapping(value = "/subtraction/{numberOne}/{numberTwo}", method=RequestMethod.GET) // RequestMapping vai mapear uma requisição para um método
	public Double subtraction(
			@PathVariable (value = "numberOne") String numberOne,
			@PathVariable (value = "numberTwo") String numberTwo
			) throws Exception {
	
		if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value.");
		}
		
		return math.subtraction(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
	}

	@RequestMapping(value = "/multiplication/{numberOne}/{numberTwo}", method=RequestMethod.GET) // RequestMapping vai mapear uma requisição para um método
	public Double multiplication(
			@PathVariable (value = "numberOne") String numberOne,
			@PathVariable (value = "numberTwo") String numberTwo
			) throws Exception {
	
		if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value.");
		}
		
		return math.multiplication(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
	}
	
	@RequestMapping(value = "/division/{numberOne}/{numberTwo}", method=RequestMethod.GET) // RequestMapping vai mapear uma requisição para um método
	public Double division(
			@PathVariable (value = "numberOne") String numberOne,
			@PathVariable (value = "numberTwo") String numberTwo
			) throws Exception {
	
		if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value.");
		}
		
		return math.division(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
	}
	
	@RequestMapping(value = "/average/{numberOne}/{numberTwo}", method=RequestMethod.GET) // RequestMapping vai mapear uma requisição para um método
	public Double average(
			@PathVariable (value = "numberOne") String numberOne,
			@PathVariable (value = "numberTwo") String numberTwo
			) throws Exception {
	
		if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value.");
		}
		
		return math.average(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
	}
	
	@RequestMapping(value = "/sqrt/{number}", method=RequestMethod.GET) // RequestMapping vai mapear uma requisição para um método
	public Double sqrt(
			@PathVariable (value = "number") String number
			) throws Exception {
	
		if (!NumberConverter.isNumeric(number)) {
			throw new UnsupportedMathOperationException("Please set a numeric value.");
		}
		
		return math.sqrt(NumberConverter.convertToDouble(number));
	}
	
}
