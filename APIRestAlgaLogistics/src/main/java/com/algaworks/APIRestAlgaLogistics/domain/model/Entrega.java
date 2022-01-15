package com.algaworks.APIRestAlgaLogistics.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.algaworks.APIRestAlgaLogistics.domain.exception.NegocioException;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Entrega {

	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
		
	@ManyToOne
	private Cliente cliente;
	
	@Embedded
	private Destinatario destinatario;

	private BigDecimal taxa;
	
	@OneToMany(mappedBy = "entrega", cascade = CascadeType.ALL)
	private List<Ocorrencia> ocorrencias = new ArrayList<>();
	
	@Enumerated(EnumType.STRING)
	private StatusEntrega status;
	
	private OffsetDateTime dataPedido;
	
	private OffsetDateTime dataFinalizacao;

	public Ocorrencia adicionarOcorrencia(String descricao) {
		Ocorrencia ocorrencia = new Ocorrencia();
		ocorrencia.setDescricao(descricao);
		ocorrencia.setDataRegistro(OffsetDateTime.now());
		ocorrencia.setEntrega(this);
		
		this.getOcorrencias().add(ocorrencia);
		
		return ocorrencia;
	}

	public void finalizar() {
		if (naoPodeSerFinalizada()) {
			throw new NegocioException("Entrega não pode ser finalizada.");
		}
		
		setStatus(StatusEntrega.FINALIZADA);		
		setDataFinalizacao(OffsetDateTime.now());
	}
	
	public boolean podeSerFinalizada() {
		return StatusEntrega.PENDENTE.equals(getStatus());
	}
	
	public boolean naoPodeSerFinalizada() {
		return !podeSerFinalizada();
	}
}

/*
	Anotações
	
	@ManyToOne
	um relacionamento (muitos para um) entre as entidades Cliente e Entrega
	@JoinColumn(name = "nome_coluna") serve para definir qual o campo de "PK"
	por padrão é o campo Id
	
	@Embedded
	para abstrair os dados de destinatário para outra classe
	mapeando as props da entidade (Destinatário), porém para usar na mesma tabela (Entrega)
	
	@Embeddable
	por usar o @Embedded, então na classe em questão, deve-se haver o @Embeddable
	
	@Enumerated(EnumType.STRING)
	armazena a string que representa a constante do ENUM
	ou seja, grava o próprio texto do ENUM
	
	@JsonProperty(access = Access.READ_ONLY)
	define como somente leitura, para evitar que seja enviado valor pra propriedade no consumo da api,
	em determinado endpoint. Então se o consumidor enviar determinado dado, ele será ignorado e informado como NULL
	
	Validation Group
	@ConvertGroup(from = Default.class, to = ValidationGroups.ClienteId.class)
	visa converter de qual será a validação
	troca a validação default pela customizada
	OBS: aplica-se para cada propriedade da classe, ou seja,
	as demais props da classe, serão validadas pelo método padrão
	
	LocalDateTime pega a hora com base no UTC e no padrão (ISO-8601)
	OffsetDateTime pega já o local, não do UTC.
	
	@OneToMany(mappedBy = "entrega", cascade = CascadeType.ALL)
	faz o mapeamento em cascata, para sincronizar informações com base entre as entidades
	
	
*/
