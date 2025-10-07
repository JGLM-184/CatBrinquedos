INSERT INTO categoria (nome) VALUES ('Veículos');
INSERT INTO categoria (nome) VALUES ('Bonecas');
INSERT INTO categoria (nome) VALUES ('Quebra-Cabeças');
INSERT INTO categoria (nome) VALUES ('Educativos');
INSERT INTO categoria (nome) VALUES ('Jogos');

INSERT INTO brinquedo (nome, marca, categoria_id, descricao, idade_ideal, imagem_principal, preco, destaque) VALUES ('Carrinho de Controle Remoto', 'FunToy', 1, 'Carrinho maneiro', '6+', '/imagens/brinquedos/Carrinho de Controle Remoto.jpg', 149.90, true);
INSERT INTO brinquedo (nome, marca, categoria_id, descricao, idade_ideal, imagem_principal, preco, destaque) VALUES ('Boneca Fashion', 'Bella', 2, 'Boneca bonita', '3+', '/imagens/brinquedos/Boneca Fashion.jpg', 89.90, false);
INSERT INTO brinquedo (nome, marca, categoria_id, descricao, idade_ideal, imagem_principal, preco, destaque) VALUES ('Quebra-Cabeça 500 peças', 'PuzzleMax', 3,  'Muito difícil esse','8+', '/imagens/brinquedos/Quebra-Cabeça 500 peças.jpg', 59.50, true);
INSERT INTO brinquedo (nome, marca, categoria_id, descricao, idade_ideal, imagem_principal, preco, destaque) VALUES ('Blocos de Montar', 'Construtor', 4, 'LEGOOO', '4+', '/imagens/brinquedos/Blocos de Montar.jpg', 79.90, true);
INSERT INTO brinquedo (nome, marca, categoria_id, descricao, idade_ideal, imagem_principal, preco, destaque) VALUES ('Futebol de Botão', 'SportsFun', 5,  'Não curto muito','7+', '/imagens/brinquedos/Futebol de Botão.jpg', 39.90, true);

INSERT INTO usuario (nome, username, senha, admin) VALUES ('Administrador', 'teste', '$2a$10$PGbBlRRc2YfnzSOXvh6PKe5GHzlJkY6UP2.LrPrv8suAmFfYykxdq', true)
INSERT INTO usuario (nome, username, senha, admin) VALUES ('Gabriel', 'gabriel', '$2a$10$PGbBlRRc2YfnzSOXvh6PKe5GHzlJkY6UP2.LrPrv8suAmFfYykxdq', false)