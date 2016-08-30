CREATE VIEW "VW_PESSOAS_DOMICILIOS" AS 
 SELECT PESSOAS_DOMICILIOS.ID AS ID,
        PESSOAS_DOMICILIOS.I_PESSOAS AS I_PESSOAS,
        PESSOAS.NOME AS NOME_PESSOA,
        PESSOAS_DOMICILIOS.I_DOMICILIOS AS I_DOMICILIOS,
        DOMICILIOS.DESCRICAO AS DESCRICAO_DOMICILIOS,
        PESSOAS_DOMICILIOS.RESPONSAVEL AS RESPONSAVEL 
   FROM PESSOAS_DOMICILIOS,  
        DOMICILIOS,
	    PESSOAS
  WHERE DOMICILIOS.ID = PESSOAS_DOMICILIOS.I_DOMICILIOS AND
	PESSOAS.ID = PESSOAS_DOMICILIOS.I_PESSOAS;

CREATE VIEW "VW_PENDENCIAS_SITUACOES" AS 
 SELECT PENDENCIAS_SITUACOES.ID AS ID,
        PENDENCIAS_SITUACOES.I_PENDENCIAS AS I_PENDENCIAS,
        PENDENCIAS_SITUACOES.DATA_CADASTRO AS DATA_CADASTRO,
        PENDENCIAS_SITUACOES.SITUACAO AS SITUACAO
   FROM PENDENCIAS_SITUACOES;

	
CREATE VIEW "VW_PESSOAS" AS 
 SELECT PESSOAS.ID AS ID,
        PESSOAS.NOME AS NOME,
		PESSOAS.SEXO AS SEXO,
        PESSOAS.CPF AS CPF,
        PESSOAS.RG AS RG,
        PESSOAS.DATA_NASCIMENTO AS DATA_NASCIMENTO,
        PESSOAS.TELEFONE_FIXO AS TELEFONE_FIXO,
        PESSOAS.TELEFONE_CELULAR AS TELEFONE_CELULAR,
        PESSOAS.EMAIL AS EMAIL,
		(SELECT PESSOAS_SITUACOES.SITUACAO
		   FROM PESSOAS_SITUACOES 
		  WHERE PESSOAS_SITUACOES.I_PESSOAS = PESSOAS.ID 
	   ORDER BY PESSOAS_SITUACOES.ID DESC 
	      LIMIT 1) AS SITUACAO_ATUAL
   FROM PESSOAS;

	
CREATE VIEW "VW_PESSOAS_SITUACOES" AS 
 SELECT PESSOAS_SITUACOES.ID AS ID,
        PESSOAS_SITUACOES.I_PESSOAS AS I_PESSOAS,
        PESSOAS_SITUACOES.DATA_CADASTRO AS DATA_CADASTRO,
        PESSOAS_SITUACOES.SITUACAO AS SITUACAO
   FROM PESSOAS_SITUACOES;
	
CREATE VIEW "VW_PENDENCIAS" AS 
 SELECT PENDENCIAS.ID AS ID,
        PENDENCIAS.I_PESSOAS AS I_PESSOAS,
		PESSOAS.NOME AS NOME_PESSOA,
        PENDENCIAS.DATA_CADASTRO AS DATA_CADASTRO,
        PENDENCIAS.DATA_PREVISTA_RESOLUCAO AS DATA_PREVISTA_RESOLUCAO,
        PENDENCIAS.TIPO_PENDENCIA AS TIPO_PENDENCIA,
        PENDENCIAS.DESCRICAO AS DESCRICAO,
		(SELECT PENDENCIAS_SITUACOES.SITUACAO
		   FROM PENDENCIAS_SITUACOES 
		  WHERE PENDENCIAS_SITUACOES.I_PENDENCIAS = PENDENCIAS.ID 
	   ORDER BY PENDENCIAS_SITUACOES.ID DESC 
	      LIMIT 1) AS SITUACAO_ATUAL
   FROM PENDENCIAS,
   	    PESSOAS
  WHERE	PESSOAS.ID = PENDENCIAS.I_PESSOAS;

	
CREATE VIEW "VW_DOMICILIOS" AS 
 SELECT DOMICILIOS.ID AS ID,
        DOMICILIOS.DESCRICAO AS DESCRICAO,
        DOMICILIOS.CEP AS CEP,
        DOMICILIOS.NUMERO AS NUMERO,
        DOMICILIOS.RUA AS RUA,
        DOMICILIOS.BAIRRO AS BAIRRO,
        DOMICILIOS.CIDADE AS CIDADE,
        DOMICILIOS.UF AS UF,
        DOMICILIOS.TELEFONE_FIXO AS TELEFONE_FIXO
   FROM DOMICILIOS;

	