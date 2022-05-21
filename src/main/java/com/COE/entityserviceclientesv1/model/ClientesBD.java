package com.COE.entityserviceclientesv1.model;

import com.COE.entityserviceclientesv1.model.Cliente;
//import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ClientesBD {

	//private  JSONObject objJSON;
	private Cliente infoCliente;

	public ClientesBD() {

	}


	public String eliminaCliente(String correo){
		JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader("BDClientes.json")){
            Object obj = jsonParser.parse(reader);
            JSONArray listaClientes = (JSONArray) obj;
            boolean existe=false;
            for(int i=0;i<listaClientes.size();i++){
                JSONObject jsonObject1 = (JSONObject)listaClientes.get(i);
                JSONObject ClienteObject = (JSONObject) jsonObject1.get("clientes");
                if(correo.equals((String)ClienteObject.get("correo"))){
                	existe=true;
                	listaClientes.remove(i);
                }
            }
            if(!existe){
            	return "Usuario no existe";
            }else{
		         
		        try (FileWriter file = new FileWriter("BDClientes.json")) {
		            file.write(listaClientes.toJSONString()); 
		            file.flush();
		 
		        } catch (IOException e) {
		            e.printStackTrace();
		        }

                return "Operacion exitosa";
        	}
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
         return "null";
	}

	public String modificaCliente(String idBusqueda, String correoNuevo, String nombre){
		JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader("BDClientes.json")){
            Object obj = jsonParser.parse(reader);
            JSONArray listaClientes = (JSONArray) obj;

        	Integer idPersistente=0;

        	for(int i=0;i<listaClientes.size();i++){
                JSONObject jsonObject1 = (JSONObject)listaClientes.get(i);
                JSONObject ClienteObject = (JSONObject) jsonObject1.get("clientes");
                if(idBusqueda.equals((String)ClienteObject.get("id"))){
                	idPersistente=Integer.parseInt((String)ClienteObject.get("id"));
                	listaClientes.remove(i);
                }
            }
			infoCliente= new Cliente(
            nombre,
            idPersistente,
            correoNuevo
            );

	        JSONObject detallesCliente = new JSONObject();
	        detallesCliente.put("id", ""+infoCliente.getID());
	        detallesCliente.put("nombre", infoCliente.getNombre());
	        detallesCliente.put("correo", infoCliente.getCorreo());
	         
	        JSONObject objetoCliente = new JSONObject(); 
	        objetoCliente.put("clientes", detallesCliente);

	        listaClientes.add(objetoCliente);
	         
	        try (FileWriter file = new FileWriter("BDClientes.json")) {
	            file.write(listaClientes.toJSONString()); 
	            file.flush();
	 
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

            return "Operacion exitosa";
        	
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
         return "null";
	}

	public String insertaCliente(String correo, String nombre){
		JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader("BDClientes.json")){
            Object obj = jsonParser.parse(reader);
            JSONArray listaClientes = (JSONArray) obj;
            boolean existe=false;
            Integer idAsignacion=listaClientes.size();
            for(int i=0;i<listaClientes.size();i++){
                JSONObject jsonObject1 = (JSONObject)listaClientes.get(i);
                JSONObject ClienteObject = (JSONObject) jsonObject1.get("clientes");
                if(correo.equals((String)ClienteObject.get("correo"))){
                	existe=true;
                }
                if(idAsignacion == Integer.parseInt((String)ClienteObject.get("id"))){
                	idAsignacion++;
                }else if(idAsignacion<Integer.parseInt((String)ClienteObject.get("id"))){
                	idAsignacion=Integer.parseInt((String)ClienteObject.get("id"))+1;
                }
            }
            if(existe){
            	return "Usuario ya existe con el correo: "+ correo;
            }else{
            	infoCliente= new Cliente(
                nombre,
                idAsignacion,
                correo
                );

		        JSONObject detallesCliente = new JSONObject();
		        detallesCliente.put("id", ""+infoCliente.getID());
		        detallesCliente.put("nombre", infoCliente.getNombre());
		        detallesCliente.put("correo", infoCliente.getCorreo());
		         
		        JSONObject objetoCliente = new JSONObject(); 
		        objetoCliente.put("clientes", detallesCliente);

		        listaClientes.add(objetoCliente);
		         
		        try (FileWriter file = new FileWriter("BDClientes.json")) {
		            file.write(listaClientes.toJSONString()); 
		            file.flush();
		 
		        } catch (IOException e) {
		            e.printStackTrace();
		        }

                return "Operacion exitosa";
        	}
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
         return "null";
	}

	public Cliente getCliente(String correo) {
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader("BDClientes.json")){
            Object obj = jsonParser.parse(reader);
            JSONArray listaClientes = (JSONArray) obj;
            for(int i=0;i<listaClientes.size();i++){
                JSONObject jsonObject1 = (JSONObject)listaClientes.get(i);
                JSONObject ClienteObject = (JSONObject) jsonObject1.get("clientes");
                if(correo.equals((String)ClienteObject.get("correo"))){
                	infoCliente= new Cliente(
                	(String)ClienteObject.get("nombre"),
                	Integer.parseInt((String)ClienteObject.get("id")),
                	(String)ClienteObject.get("correo")
                	);
                	return infoCliente;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
         return null;
	}
	
}