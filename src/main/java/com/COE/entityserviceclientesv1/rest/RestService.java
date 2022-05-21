package com.COE.entityserviceclientesv1.rest;

import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.asm.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.COE.entityserviceclientesv1.model.ClientesBD;
import com.COE.entityserviceclientesv1.model.Cliente;
//import com.everis.dar.springairline.model.Flight;
//import com.everis.dar.springairline.model.Passenger;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name="RestService", description="Rest del examen")
public class RestService {

	Logger logger = LoggerFactory.getLogger(RestService.class);

	@Autowired
	private ClientesBD objClientesBD = new ClientesBD();



	@Operation(summary = "eliminacliente", description = "eliminacliente", tags = { "RestService" })
	@PostMapping("/eliminacliente")
	public @ResponseBody String eliminacliente(
			@Parameter(description="eliminacliente correo", required = true, example="leo@gmail.com", in = ParameterIn.QUERY ) @RequestParam(name = "correo") String correo) {
		logger.debug("eliminacliente: /correo/" + correo);

		try {
			String infoConsulta=objClientesBD.eliminaCliente(correo);
			return infoConsulta;

		} catch (Exception e) {
			logger.error("Error, no existe el ususario con correo " + correo);
		}

		return "eliminacliente no encontrado";
	}

	@Operation(summary = "modificacliente", description = "modificacliente", tags = { "RestService" })
	@PostMapping("/modificacliente")
	public @ResponseBody String modificacliente(
		@Parameter(description="modificacliente idBusqueda", required = true, example="1", in = ParameterIn.QUERY ) @RequestParam(name = "idBusqueda") String idBusqueda,
		@Parameter(description="modificacliente correoNuevo", required = true, example="leo@gmail.com", in = ParameterIn.QUERY ) @RequestParam(name = "correoNuevo") String correoNuevo,
		@Parameter(description="modificacliente nombre", required = true, example="Leopoldo", in = ParameterIn.QUERY ) @RequestParam(name = "nombre") String nombre) {
		logger.debug("modificacliente: /correo/" + idBusqueda);

		try {
			String info=objClientesBD.modificaCliente(idBusqueda, correoNuevo, nombre);
			return info;
		} catch (Exception e) {
			logger.error("Error, ocurrio un problema");
		}

		return "Ocurrio un problema";
	}

	@Operation(summary = "creacliente", description = "creacliente", tags = { "RestService" })
	@PostMapping("/creacliente")
	public @ResponseBody String creacliente(
		@Parameter(description="creacliente correo", required = true, example="leo@gmail.com", in = ParameterIn.QUERY ) @RequestParam(name = "correo") String correo,
		@Parameter(description="creacliente nombre", required = true, example="Leopoldo", in = ParameterIn.QUERY ) @RequestParam(name = "nombre") String nombre) {
		logger.debug("creacliente: /correo/" + correo);

		try {
			String infoCrea=objClientesBD.insertaCliente(correo, nombre);
			return infoCrea;
		} catch (Exception e) {
			logger.error("Error, ocurrio un problema");
		}

		return "Ocurrio un problema";
	}

	@Operation(summary = "consultacliente", description = "consultacliente", tags = { "RestService" })
	@PostMapping("/consultacliente")
	public @ResponseBody String consultacliente(
			@Parameter(description="consultacliente correo", required = true, example="leo@gmail.com", in = ParameterIn.QUERY ) @RequestParam(name = "correo") String correo) {
		logger.debug("consultacliente: /correo/" + correo);

		try {
			Cliente infoConsulta=objClientesBD.getCliente(correo);
			return "ID: "+infoConsulta.getID()+"\nNombre: "+infoConsulta.getNombre()+"\nCorreo: "+infoConsulta.getCorreo();

		} catch (Exception e) {
			logger.error("Error, no existe el ususario con correo " + correo);
		}

		return "consultacliente no encontrado";
	}

}