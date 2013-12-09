package org.alterq.mvc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import org.alterq.domain.Round;
import org.alterq.repo.RoundDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.google.gson.Gson;


@Controller
@RequestMapping(value = "/apuesta")
public class ApuestaController {
    @Autowired
    private RoundDao jornadaDao;

    @RequestMapping(method = RequestMethod.GET)
    public String displaySortedMembers(Model model) {

    //Harcodeamos la peticion JSON y dejamos los datos en la variable 'json'
    //String json = "{temporada:2013, jornada:9, partidos : [{id:1, equipo1:VILLARREAL, equipo2:VALENCIA}, {id:2, equipo1:ELCHE, equipo2:GRANADA}, {id:3, equipo1:'AT. MADRID', equipo2:BETIS}, {id:4, equipo1:LEVANTE, equipo2:ESPANYOL}, {id:5, equipo1:MÁLAGA, equipo2:CELTA}, {id:6, equipo1:SEVILLA, equipo2:OSASUNA}, {id:7, equipo1:'R. SOCIEDAD', equipo2:ALMERÍA}, {id:8, equipo1:RECREATIVO, equipo2:MALLORCA}, {id:9, equipo1:TENERIFE, equipo2:DEPORTIVO}, {id:10, equipo1:ZARAGOZA, equipo2:ALAVÉS}, {id:11, equipo1:MIRANDÉS, equipo2:NUMANCIA}, {id:12, equipo1:HÉRCULES, equipo2:JAEN}, {id:13, equipo1:MURCIA, equipo2:CÓRDOBA}, {id:14, equipo1:LUGO, equipo2:SABADELL}, {id:15, equipo1:BARCELONA, equipo2:'R. MADRID'}]}";
	//extraemos los datos del JSON y se los pasamos al JSP
    //Gson gson = new Gson();
    //Jornada  jornada = (Jornada) gson.fromJson(json, Jornada.class);
    
    //Consultamos los datos de la BBDD y devolviendo una clase 'Jornada' para la temporada 2013, jornada 9
    //Jornada jornada = new Jornada(); 
    //jornada = jornadaDao.findByTemporadaJornada(2013, 9);

    	
    //Hacemos la peticion de los datos al WS para que nos devuelva el JSON (temporada 2013 Jornada 9)
    Round jornada = new Round();
    int temp=2013;
    int jorn=9;
    String json = readUrl("http://127.0.0.1:8080/quinimobile/apuesta/jornada?temporada="+temp+"$jornada="+jorn);
    Gson gson = new Gson();        
    jornada = gson.fromJson(json, Round.class);

    //se los pasamos al modelo para que los muestre en el cliente
    model.addAttribute("temporada", jornada.getSeason());
    model.addAttribute("jornada", jornada.getRound());
    model.addAttribute("partidos", jornada.getGames());

    return "apuesta";
    }

    @RequestMapping(method=RequestMethod.GET, produces="application/json",value="jornada")
    public @ResponseBody Round getJornada()
    {
        return jornadaDao.findByTemporadaJornada(2013, 9);
    }

    @RequestMapping(method=RequestMethod.GET, produces="application/json",value="jornada/{jornada}/temporada/{temporada}")
    public @ResponseBody Round getJornada(@PathVariable("temporada") int temporada, @PathVariable("jornada") int jornada)
    {
        return jornadaDao.findByTemporadaJornada(temporada, jornada);
    }
    
    @RequestMapping(method=RequestMethod.GET, produces="application/json",value="jornada", params = {"temporada", "jornada"})
    public @ResponseBody Round getJornadaParams(@RequestParam(value = "temporada") int temporada, @RequestParam(value = "jornada") int jornada)
    {
        return jornadaDao.findByTemporadaJornada(temporada, jornada);
    }
    public String readUrl(String usrserv) {
		String rdo = "";
    	try {
    		URL url = new URL(usrserv);
    		//read text returned by server
    		BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
    		String line;
    		while ((line = in.readLine()) != null) {
    			rdo+=line;
    		}
    		in.close();
    	}
    	catch (MalformedURLException e) {
    		System.out.println("Malformed URL: " + e.getMessage());
    		return null;
    	}
    	catch (IOException e) {
    		System.out.println("I/O Error: " + e.getMessage());
    		return null;
    	}
    	return rdo;
    }
}

