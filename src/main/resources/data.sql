-- Perguntas de múltipla escolha
INSERT INTO pergunta (enunciado, tipo, nivel, categoria) VALUES
('Qual é o tipo de dado usado para armazenar números inteiros em Java?', 'MULTIPLA_ESCOLHA', 'INICIANTE', 'TIPOS_DE_DADOS'),
('O que significa POO?', 'MULTIPLA_ESCOLHA', 'INICIANTE', 'CONCEITOS'),
('Qual palavra-chave é usada para herança em Java?', 'MULTIPLA_ESCOLHA', 'INTERMEDIARIO', 'OOP'),
('Qual é a saída de: System.out.println(10 / 3)?', 'MULTIPLA_ESCOLHA', 'INICIANTE', 'OPERADORES');

-- Perguntas de verdadeiro/falso
INSERT INTO pergunta (enunciado, tipo, nivel, categoria) VALUES
('Em Java, uma interface pode ter métodos com implementação usando a palavra-chave default.', 'VERDADEIRO_FALSO', 'INTERMEDIARIO', 'OOP'),
('O tipo String em Java é uma classe, não um tipo primitivo.', 'VERDADEIRO_FALSO', 'INICIANTE', 'TIPOS_DE_DADOS'),
('Java permite herança múltipla de classes.', 'VERDADEIRO_FALSO', 'INTERMEDIARIO', 'OOP');

-- Perguntas de completar código
INSERT INTO pergunta (enunciado, tipo, nivel, categoria) VALUES
('Complete o código para imprimir "Hello, World!" em Java: System.out.___("Hello, World!");', 'COMPLETAR_CODIGO', 'INICIANTE', 'SINTAXE'),
('Complete: para declarar uma variável inteira chamada "idade" com valor 20: ___ idade = 20;', 'COMPLETAR_CODIGO', 'INICIANTE', 'TIPOS_DE_DADOS');

-- Opções para pergunta 1 (int)
INSERT INTO opcao (texto, correta, pergunta_id) VALUES
('int', true, 1),
('float', false, 1),
('String', false, 1),
('boolean', false, 1);

-- Opções para pergunta 2 (POO)
INSERT INTO opcao (texto, correta, pergunta_id) VALUES
('Programação Orientada a Objetos', true, 2),
('Programação Orientada a Operações', false, 2),
('Processamento Orientado a Objetos', false, 2),
('Programação Organizada em Ordem', false, 2);

-- Opções para pergunta 3 (herança)
INSERT INTO opcao (texto, correta, pergunta_id) VALUES
('extends', true, 3),
('implements', false, 3),
('inherits', false, 3),
('super', false, 3);

-- Opções para pergunta 4 (10/3)
INSERT INTO opcao (texto, correta, pergunta_id) VALUES
('3', true, 4),
('3.33', false, 4),
('3.0', false, 4),
('4', false, 4);

-- Opções para pergunta 5 (interface default - VERDADEIRO)
INSERT INTO opcao (texto, correta, pergunta_id) VALUES
('Verdadeiro', true, 5),
('Falso', false, 5);

-- Opções para pergunta 6 (String é classe - VERDADEIRO)
INSERT INTO opcao (texto, correta, pergunta_id) VALUES
('Verdadeiro', true, 6),
('Falso', false, 6);

-- Opções para pergunta 7 (herança múltipla - FALSO)
INSERT INTO opcao (texto, correta, pergunta_id) VALUES
('Verdadeiro', false, 7),
('Falso', true, 7);

-- Opções para pergunta 8 (println)
INSERT INTO opcao (texto, correta, pergunta_id) VALUES
('println', true, 8),
('print', false, 8),
('write', false, 8),
('show', false, 8);

-- Opções para pergunta 9 (int)
INSERT INTO opcao (texto, correta, pergunta_id) VALUES
('int', true, 9),
('integer', false, 9),
('num', false, 9),
('var', false, 9);
