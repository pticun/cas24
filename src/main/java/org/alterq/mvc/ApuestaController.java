package org.alterq.mvc;

//import java.util.List;

import java.util.List;

import org.alterq.domain.Jornada;
import org.alterq.domain.Member;
import org.alterq.repo.JornadaDao;
//import org.jboss.tools.example.springmvc.domain.Partido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

//import com.google.gson.Gson;

@Controller
@RequestMapping(value = "/apuesta")
public class ApuestaController {
    @Autowired
    private JornadaDao jornadaDao;

    @RequestMapping(method = RequestMethod.GET)
    public String displaySortedMembers(Model model) {

    //Consultamos los datos de la BBDD y devolviendo una clase 'Jornada' para la temporada 2013, jornada 9
    Jornada jornada = new Jornada(); 
    jornada = jornadaDao.findByTemporadaJornada(2013, 9);
    
    //Harcodeamos la peticion JSON y dejamos los datos en la variable 'json'
    //String json = "{temporada:2013, jornada:9, partidos : [{id:1, equipo1:VILLARREAL, equipo2:VALENCIA}, {id:2, equipo1:ELCHE, equipo2:GRANADA}, {id:3, equipo1:'AT. MADRID', equipo2:BETIS}, {id:4, equipo1:LEVANTE, equipo2:ESPANYOL}, {id:5, equipo1:MÁLAGA, equipo2:CELTA}, {id:6, equipo1:SEVILLA, equipo2:OSASUNA}, {id:7, equipo1:'R. SOCIEDAD', equipo2:ALMERÍA}, {id:8, equipo1:RECREATIVO, equipo2:MALLORCA}, {id:9, equipo1:TENERIFE, equipo2:DEPORTIVO}, {id:10, equipo1:ZARAGOZA, equipo2:ALAVÉS}, {id:11, equipo1:MIRANDÉS, equipo2:NUMANCIA}, {id:12, equipo1:HÉRCULES, equipo2:JAEN}, {id:13, equipo1:MURCIA, equipo2:CÓRDOBA}, {id:14, equipo1:LUGO, equipo2:SABADELL}, {id:15, equipo1:BARCELONA, equipo2:'R. MADRID'}]}";
	//extraemos los datos del JSON y se los pasamos al JSP
    //Gson gson = new Gson();
    //Jornada  jornada = (Jornada) gson.fromJson(json, Jornada.class);
    
    model.addAttribute("temporada", jornada.getTemporada());
    model.addAttribute("jornada", jornada.getJornada());
    model.addAttribute("partidos", jornada.getPartidos());

    return "apuesta";
    }

    @RequestMapping(method=RequestMethod.GET, produces="application/json",value="jornada")
    public @ResponseBody Jornada getJornada()
    {
        return jornadaDao.findByTemporadaJornada(2013, 9);
    }

    @RequestMapping(method=RequestMethod.GET, produces="application/json",value="jornada/{jornada}/temporada/{temporada}")
    public @ResponseBody Jornada getJornada(@PathVariable("temporada") int temporada, @PathVariable("jornada") int jornada)
    {
        return jornadaDao.findByTemporadaJornada(temporada, jornada);
    }
    
    @RequestMapping(method=RequestMethod.GET, produces="application/json",value="jornada", params = {"temporada", "jornada"})
    public @ResponseBody Jornada getJornadaParams(@RequestParam(value = "temporada") int temporada, @RequestParam(value = "jornada") int jornada)
    {
        return jornadaDao.findByTemporadaJornada(temporada, jornada);
    }
    
}
