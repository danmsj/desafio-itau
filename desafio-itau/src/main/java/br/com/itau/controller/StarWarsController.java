package br.com.itau.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.itau.param.ParamEspecie;
import br.com.itau.param.ParamFilme;
import br.com.itau.param.ParamNave;
import br.com.itau.param.ParamPersonagem;
import br.com.itau.param.ParamPlaneta;
import br.com.itau.param.ParamVeiculo;
import br.com.itau.service.StarWarsService;
import br.com.itau.vo.AcessosVo;
import br.com.itau.vo.EspecieVo;
import br.com.itau.vo.FilmeVo;
import br.com.itau.vo.NaveVo;
import br.com.itau.vo.PersonagemVo;
import br.com.itau.vo.PlanetaVo;
import br.com.itau.vo.ResultadoEspecieVo;
import br.com.itau.vo.ResultadoFilmeVo;
import br.com.itau.vo.ResultadoNaveVo;
import br.com.itau.vo.ResultadoPersonagemVo;
import br.com.itau.vo.ResultadoPlanetaVo;
import br.com.itau.vo.ResultadoVeiculoVo;
import br.com.itau.vo.VeiculoVo;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/starwars", produces = { MediaType.APPLICATION_JSON_VALUE })
public class StarWarsController {

	@Autowired
	private StarWarsService starWarsService;

	@GetMapping("/acessos")
	public List<AcessosVo> findAllAcessos() {
		return this.starWarsService.findAllAcessos();

	}
	
	@GetMapping("/personagem/{id}")
	public PersonagemVo findByIdPersonagem(@PathVariable Long id) {
		return this.starWarsService.findByIdPersonagem(id);

	}

	@GetMapping("/personagem")
	public ResultadoPersonagemVo findAllPersonagem(@ModelAttribute ParamPersonagem params) {
		return this.starWarsService.findAllPersonagem(params);

	}

	@GetMapping("/planeta/{id}")
	public PlanetaVo findByIdPlaneta(@PathVariable Long id) {
		return this.starWarsService.findByIdPlaneta(id);

	}

	@GetMapping("/planeta")
	public ResultadoPlanetaVo findAllPlaneta(@ModelAttribute ParamPlaneta params) {
		return this.starWarsService.findAllPlaneta(params);
	}

	@GetMapping("/veiculo/{id}")
	public VeiculoVo findByIdVeiculo(@PathVariable Long id) {
		return this.starWarsService.findByIdVeiculo(id);

	}

	@GetMapping("/veiculo")
	public ResultadoVeiculoVo findAllVeiculo(@ModelAttribute ParamVeiculo params) {
		return this.starWarsService.findAllVeiculo(params);

	}
	
	@GetMapping("/nave/{id}")
	public NaveVo findByIdNave(@PathVariable Long id) {
		return this.starWarsService.findByIdNave(id);

	}

	@GetMapping("/nave")
	public ResultadoNaveVo findAllNave(@ModelAttribute ParamNave params) {
		return this.starWarsService.findAllNave(params);

	}
	
	@GetMapping("/especie/{id}")
	public EspecieVo findByIdEspecie(@PathVariable Long id) {
		return this.starWarsService.findByIdEspecie(id);

	}

	@GetMapping("/especie")
	public ResultadoEspecieVo findAllEspecie(@ModelAttribute ParamEspecie params) {
		return this.starWarsService.findAllEspecie(params);

	}
	
	@GetMapping("/filme/{id}")
	public FilmeVo findByIdFilme(@PathVariable Long id) {
		return this.starWarsService.findByIdFilme(id);

	}

	@GetMapping("/filme")
	public ResultadoFilmeVo findAllFilme(@ModelAttribute ParamFilme params) {
		return this.starWarsService.findAllFilme(params);

	}
}
