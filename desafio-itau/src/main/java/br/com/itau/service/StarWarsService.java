package br.com.itau.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.itau.feign.FeignStarWars;
import br.com.itau.model.Contador;
import br.com.itau.param.ParamEspecie;
import br.com.itau.param.ParamFilme;
import br.com.itau.param.ParamNave;
import br.com.itau.param.ParamPersonagem;
import br.com.itau.param.ParamPlaneta;
import br.com.itau.param.ParamVeiculo;
import br.com.itau.repository.ContadorRepository;
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

@Service
public class StarWarsService {
	public static final String URL_PERSONAGEM = "http://localhost:8080/starwars/personagem";
	public static final String URL_PLANETA = "http://localhost:8080/starwars/planeta";
	public static final String URL_FILME = "http://localhost:8080/starwars/filme";
	public static final String URL_NAVE = "http://localhost:8080/starwars/nave";
	public static final String URL_VEICULO = "http://localhost:8080/starwars/veiculo";
	public static final String URL_ESPECIE = "http://localhost:8080/starwars/especie";
	
	@Autowired
	private FeignStarWars feignStarWars;
	@Autowired
	private ContadorRepository contadorRepository;

	public PersonagemVo findByIdPersonagem(Long id) {
		PersonagemVo planetaPersonagem = new PersonagemVo();
		
		planetaPersonagem = this.feignStarWars.findByIdPersonagem(id);
		String[] planeta = planetaPersonagem.getHomeworld().split("/");
		Long idPlaneta = Long.parseLong(planeta[planeta.length - 1]);
		PlanetaVo planetaVo = findByIdPlaneta(idPlaneta);

		String urlPersonagem = URL_PERSONAGEM;
		setContadorAcesso(urlPersonagem+id);
		
		List<String> url = new ArrayList<String>();
		int contador = 0;
		for (String personagemRelacionados : planetaVo.getResidents()) {
			if (contador < 3) {
				String[] personagem = personagemRelacionados.split("/");
				Long idPersonagem = Long.parseLong(personagem[personagem.length - 1]);

				if (id.compareTo(idPersonagem) != 0) {
					url.add(urlPersonagem + "/" + idPersonagem);
					contador++;
				}
			}

		}

		planetaPersonagem.setPersonagemRelacionadoPlaneta(url);

		return planetaPersonagem;
	}

	public ResultadoPersonagemVo findAllPersonagem(ParamPersonagem params) {
		setContadorAcesso(URL_PERSONAGEM);
		return this.feignStarWars.findAllPersonagem(params.getName());
	}

	public PlanetaVo findByIdPlaneta(Long id) {
		setContadorAcesso(URL_PLANETA);
		return this.feignStarWars.findByIdPlaneta(id);
	}

	public ResultadoPlanetaVo findAllPlaneta(ParamPlaneta params) {
		setContadorAcesso(URL_PLANETA);
		return this.feignStarWars.findAllPlaneta(params.getName());
	}
	
	public VeiculoVo findByIdVeiculo(Long id) {
		setContadorAcesso(URL_VEICULO);
		return this.feignStarWars.findByIdVeiculo(id);
	}

	public ResultadoVeiculoVo findAllVeiculo(ParamVeiculo params) {
		setContadorAcesso(URL_VEICULO);
		return this.feignStarWars.findAllVeiculo(params.getName());
	}
	
	public NaveVo findByIdNave(Long id) {
		setContadorAcesso(URL_NAVE);
		return this.feignStarWars.findByIdNave(id);
	}

	public ResultadoNaveVo findAllNave(ParamNave params) {
		setContadorAcesso(URL_NAVE);
		return this.feignStarWars.findAllNave(params.getName());
	}
	
	public EspecieVo findByIdEspecie(Long id) {
		setContadorAcesso(URL_ESPECIE);
		return this.feignStarWars.findByIdEspecie(id);
	}

	public ResultadoEspecieVo findAllEspecie(ParamEspecie params) {
		setContadorAcesso(URL_ESPECIE);
		return this.feignStarWars.findAllEspecie(params.getName());
	}

	public FilmeVo findByIdFilme(Long id) {
		setContadorAcesso(URL_FILME);
		return this.feignStarWars.findByIdFilme(id);
	}

	public ResultadoFilmeVo findAllFilme(ParamFilme params) {
		setContadorAcesso(URL_FILME);
		return this.feignStarWars.findAllFilme(params.getName());
	}
	public void setContadorAcesso(String path) {
		Contador contador = null;
		contador = this.contadorRepository.findFirstByUrlOrderByIdDesc(path).orElse(null);
		if (contador != null) {
			contador.setContador(contador.getContador() + 1);
		} else {
			contador = Contador.builder().url(path).contador(1L).build();
		}
		this.contadorRepository.saveAndFlush(contador);
	}

	public List<AcessosVo> findAllAcessos() {
		List<AcessosVo> acessos = new ArrayList<AcessosVo>();
		List<Contador> contador = this.contadorRepository.findAll(Sort.by(Direction.DESC,"contador"));
		for(Contador c:contador) {
			AcessosVo acesso = new AcessosVo();
			acesso.setUrl(c.getUrl());
			acesso.setContador(c.getContador());
			acessos.add(acesso);
		}
		return acessos;
	}

}
