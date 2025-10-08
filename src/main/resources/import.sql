INSERT INTO categoria (nome) VALUES ('Veículos');
INSERT INTO categoria (nome) VALUES ('Bonecas');
INSERT INTO categoria (nome) VALUES ('Quebra-Cabeças');
INSERT INTO categoria (nome) VALUES ('Educativos');
INSERT INTO categoria (nome) VALUES ('Jogos de Tabuleiro');
INSERT INTO categoria (nome) VALUES ('Pelúcias');
INSERT INTO categoria (nome) VALUES ('Esportes');
INSERT INTO categoria (nome) VALUES ('Brinquedos Musicais');
INSERT INTO categoria (nome) VALUES ('Ciência e Experimentos');
INSERT INTO categoria (nome) VALUES ('Super-Heróis');
INSERT INTO categoria (nome) VALUES ('Cartas');
INSERT INTO categoria (nome) VALUES ('Eletrônicos');
INSERT INTO categoria (nome) VALUES ('Natal');
INSERT INTO categoria (nome) VALUES ('Halloween');

--Veículos--
INSERT INTO brinquedo (nome, marca, categoria_id, descricao, idade_ideal, imagem_principal, preco, destaque) VALUES ('Carrinho de Controle Remoto', 'FunToy', 1, 'Carrinho maneiro', '6+', '/imagens/brinquedos/Carrinho de Controle Remoto.jpg', 149.90, false);
INSERT INTO brinquedo (nome, marca, categoria_id, descricao, idade_ideal, imagem_principal, preco, destaque) VALUES ('Carro Relâmpago Marquinhos', 'Líder Brinquedos', 1, 'McQueen volta à tona com seu melhor amigo Mate. E precisará levar seus passaportes em um mundo de intrigas, ação e comédia ao redor do mundo. Com um novo chefe, eles irão disputar a Corrida dos Campeões. Seu filho não vai querer ficar fora dessa corrida.', '4+', '/imagens/brinquedos/CarroRelampagoMC.png', 95.89, false);
INSERT INTO brinquedo (nome, marca, categoria_id, descricao, idade_ideal, imagem_principal, preco, destaque) VALUES ('Carrinho de Controle Remoto Bem 10', 'Candide', 1, 'Veículo de rádio controle de 7 funções. Pronto para brincar. Utiliza 4 pilhas AA no carro e 1 bateria 9V no controle (inclusas para demonstração).', '7+', '/imagens/brinquedos/CarroBen10.png', 149.90, false);

--Bonecas--
INSERT INTO brinquedo (nome, marca, categoria_id, descricao, idade_ideal, imagem_principal, preco, destaque) VALUES ('Boneca Fashion', 'Bella', 2, 'Boneca bonita', '3+', '/imagens/brinquedos/Boneca Fashion.jpg', 89.90, false);
INSERT INTO brinquedo (nome, marca, categoria_id, descricao, idade_ideal, imagem_principal, preco, destaque) VALUES ('Boneca Aniversário Draculaura -Monster High', 'Mattel', 2, 'Ela usa um vestido rosa acetinado com uma sobreposição de saia de teia de aranha e luvas pretas. Um colar de "pérolas" cobre seus ombros, enquanto os saltos inspirados em laços adicionam um toque fantástico ao seu look!', '10+', '/imagens/brinquedos/BonecaDraculaura.png', 329.90, false);
INSERT INTO brinquedo (nome, marca, categoria_id, descricao, idade_ideal, imagem_principal, preco, destaque) VALUES ('Sylvanian Families - Família dos Coelhos Chocolate', 'EPOCH', 2, 'Conjunto de quatro bonecos: papai Frasier, mamãe Teri, menina Freya e menino Coco. Cada boneco tem as pontas das orelhas cor de chocolate.', '10+', '/imagens/brinquedos/Sylvanian.png', 214.99, true);

--Quebra-Cabeças--
INSERT INTO brinquedo (nome, marca, categoria_id, descricao, idade_ideal, imagem_principal, preco, destaque) VALUES ('Quebra-Cabeça 500 peças', 'PuzzleMax', 3, 'Muito difícil esse','8+', '/imagens/brinquedos/Quebra-Cabeça 500 peças.jpg', 59.50, false);
INSERT INTO brinquedo (nome, marca, categoria_id, descricao, idade_ideal, imagem_principal, preco, destaque) VALUES ('Quebra-Cabeça - 1.000 Peças - Game Office - Brasil', 'Toyster', 3, 'Quebra-cabeça de 1.000 peças homenageando a cultura, paisagem e turismo do Brasil. Temos um padrão internacional de qualidade e encaixe suave que pode ser sentido em cada peça!', '8+', '/imagens/brinquedos/QuebraCabecaBrasil.png', 159.90, false);
INSERT INTO brinquedo (nome, marca, categoria_id, descricao, idade_ideal, imagem_principal, preco, destaque) VALUES ('Quebra-cabeça: Vincent Van Gogh: A Noite Estrelada - 500 peças', 'Toyster', 3, 'Desafiador! Peças tamanho NANO. Tecnologia Soft-Click: padrão internacional de qualidade e encaixe suave que pode ser sentido em cada peça.  Feito com papelão holandês, muito resistente. Embalagem super encapada com abertura lateral.', '8+', '/imagens/brinquedos/QuebraCabecaVanGogh.png', 28.90, false);

--Educativos--
INSERT INTO brinquedo (nome, marca, categoria_id, descricao, idade_ideal, imagem_principal, preco, destaque) VALUES ('Blocos de Montar', 'Construtor', 4, 'LEGOOO', '4+', '/imagens/brinquedos/Blocos de Montar.jpg', 79.90, false);
INSERT INTO brinquedo (nome, marca, categoria_id, descricao, idade_ideal, imagem_principal, preco, destaque) VALUES ('Kit de Brinquedos Educativos - Montar e Encaixar', 'Brinquedos de Montar', 4, 'Auxilia no desenvolvimento cognitivo, coordenação motora, estimulação sensorial, criatividade e imaginação.', '1+', '/imagens/brinquedos/BrinquedoMontar.png', 149.90, false);
INSERT INTO brinquedo (nome, marca, categoria_id, descricao, idade_ideal, imagem_principal, preco, destaque) VALUES ('3 Brinquedos Educativos', 'Kendy', 4, 'Toda fase da vida é importante e o desenvolvimento infantil é nossa prioridade. Brinquedos Educativos: Aprender Brincando é Sempre Mais Divertido!', '1+', '/imagens/brinquedos/3BrinquedosEducativos.png', 59.90, true);

--Jogos de Tabuleiro--
INSERT INTO brinquedo (nome, marca, categoria_id, descricao, idade_ideal, imagem_principal, preco, destaque) VALUES ('Futebol de Botão', 'SportsFun', 5,  'Não curto muito','7+', '/imagens/brinquedos/Futebol de Botão.jpg', 39.90, false);
INSERT INTO brinquedo (nome, marca, categoria_id, descricao, idade_ideal, imagem_principal, preco, destaque) VALUES ('Jogo Lig4', 'Estrela', 5, 'Alinhe as fichas da mesma cor para ganhar o jogo! Desafie sua estratégia e divirta-se com este clássico jogo de tabuleiro. Jogo de tabuleiro de tamanho médio e multicolorido, perfeito para diversão em família ou com amigos.', '10+', '/imagens/brinquedos/Lig4.png', 56.99, false);
INSERT INTO brinquedo (nome, marca, categoria_id, descricao, idade_ideal, imagem_principal, preco, destaque) VALUES ('Jogo de Tabuleiro Ludo', 'Coluna', 5, 'Jogo de tabuleiro ludo. 01 unidade. Tabuleiro em papel cartão.', '12+', '/imagens/brinquedos/Ludo.png', 18.50, false);

--Pelúcias--
INSERT INTO brinquedo (nome, marca, categoria_id, descricao, idade_ideal, imagem_principal, preco, destaque) VALUES ('Urso de Pelúcia Gigante', 'SoftBear', 6, 'Urso gigante feito com material macio e antialérgico. Mede 80 cm e é ideal para abraços, decoração e conforto na hora de dormir. Um presente encantador para qualquer idade.', '3+', '/imagens/brinquedos/UrsoPelucia.jpg', 199.90, true);
INSERT INTO brinquedo (nome, marca, categoria_id, descricao, idade_ideal, imagem_principal, preco, destaque) VALUES ('Labubu Mini Boneco Monstro Fofo', 'Pop Mart', 6, 'É o melhor item do nosso estoque, seu filho vai amar.', '0+', '/imagens/brinquedos/Labubu.png', 899.90, true);
INSERT INTO brinquedo (nome, marca, categoria_id, descricao, idade_ideal, imagem_principal, preco, destaque) VALUES ('Pelúcia Hatsune Miku', 'Chuyin Future', 6, 'São feitos de enchimentos ricos de algodão PP, algodão de seda amigável para a pele e pelúcia macia, proporcionando uma sensação melhor da pele.', '6+', '/imagens/brinquedos/HatsuneMiku.png', 249.99, true);

--Esportes--
INSERT INTO brinquedo (nome, marca, categoria_id, descricao, idade_ideal, imagem_principal, preco, destaque) VALUES ('Bola de Futebol Palmeiras', 'KickBall', 7, 'Bola leve, resistente e segura, feita para crianças. Ideal para jogos em parques e praias, estimula o exercício físico e o trabalho em equipe de forma divertida.', '5+', '/imagens/brinquedos/BolaFutebol.png', 49.90, true);
INSERT INTO brinquedo (nome, marca, categoria_id, descricao, idade_ideal, imagem_principal, preco, destaque) VALUES ('Mini Mesa de Sinuca', 'Braskit', 7, 'Para dois ou mais jogadores. Habilidade e raciocínio. Diversão garantida. Seja o novo baianinho de Mauá.', '8+', '/imagens/brinquedos/MesaSinuca.png', 205.90, false);
INSERT INTO brinquedo (nome, marca, categoria_id, descricao, idade_ideal, imagem_principal, preco, destaque) VALUES ('Kit Basquete Infantil', 'Pais & Filhos', 7, 'Inflar a bola, montar o suporte com 4 peças e encha a base com água para maior estabilidade. Bombinha e bola inclusas.', '7+', '/imagens/brinquedos/KitBasquete.png', 66.20, false);	

--Brinquedos Musicais--
INSERT INTO brinquedo (nome, marca, categoria_id, descricao, idade_ideal, imagem_principal, preco, destaque) VALUES ('Mini Piano Infantil', 'MusicFun', 8, 'Mini piano com teclas coloridas e sons realistas. Estimula a criatividade, coordenação e interesse musical das crianças. Feito com material durável e seguro.', '4+', '/imagens/brinquedos/MiniPiano.png', 129.90, false);
INSERT INTO brinquedo (nome, marca, categoria_id, descricao, idade_ideal, imagem_principal, preco, destaque) VALUES ('Conjunto de bateria elétrica', 'ROCKSOCKI', 8, 'A bateria eletrônica Rocksocki vêm com todos os componentes que você precisa para começar: 3 toms, 1 chimbal HI-HA, 1 prato SNARE, 1 prato RIDE, 1 prato CRASH e 2 pedais para o bumbo e chimbal HI-HAT.', '9+', '/imagens/brinquedos/BateriaGuitarHero.png', 349.50, true);
INSERT INTO brinquedo (nome, marca, categoria_id, descricao, idade_ideal, imagem_principal, preco, destaque) VALUES ('Flauta Doce', 'FunToys', 8, 'Flauta musical plastica colorida. Cores diversas. Medida - 30 cm × 03 cm.', '6+', '/imagens/brinquedos/Flauta.png', 8.90, false);

--Ciência e Experimentos--
INSERT INTO brinquedo (nome, marca, categoria_id, descricao, idade_ideal, imagem_principal, preco, destaque) VALUES ('Kit de Experimentos Científicos', 'GILBERT Nuclear Physics', 9, 'Kit com tubos de ensaio e reagentes seguros. Permite realizar experiências educativas e divertidas, despertando a curiosidade e o gosto pela ciência.', '8+', '/imagens/brinquedos/KitCientifico.png', 159.90, true);
INSERT INTO brinquedo (nome, marca, categoria_id, descricao, idade_ideal, imagem_principal, preco, destaque) VALUES ('Laboratório Manual do Mundo 85 Experiências', 'Nig Brinquedos', 9, 'Com 85 experiências, acompanha 11 reagentes químicos, tubos de ensaio e pipeta. Para se divertir, aprender e brincar', '+10', '/imagens/brinquedos/ManualDoMundo.png', 108.50, false);
INSERT INTO brinquedo (nome, marca, categoria_id, descricao, idade_ideal, imagem_principal, preco, destaque) VALUES ('Microscópio Infantil', 'Polibrinq', 9, 'Microscopio de brinquedo para despertar a curiosidade natural das criancas e introduzi-las a emocionante exploracao do mundo microbiano. Ampliação de até 1200x e iluminação em Led', '10+', '/imagens/brinquedos/Microscopio.png', 116.00, false);

--Super-Heróis--
INSERT INTO brinquedo (nome, marca, categoria_id, descricao, idade_ideal, imagem_principal, preco, destaque) VALUES ('Boneco do Homem de Ferro', 'Power Force', 10, 'Boneco articulado do Homem de Ferro com detalhes fiéis. Ideal para colecionar ou brincar, estimula a imaginação e garante horas de diversão heroica.', '5+', '/imagens/brinquedos/HomemDeFerro.png', 119.90, false);
INSERT INTO brinquedo (nome, marca, categoria_id, descricao, idade_ideal, imagem_principal, preco, destaque) VALUES ('Boneco Chapolin Colorado', 'Artesanal', 10, 'Feito em impressora 3D.', '6+', '/imagens/brinquedos/Chapolin.png', 69.90, false);

--Cartas--
INSERT INTO brinquedo (nome, marca, categoria_id, descricao, idade_ideal, imagem_principal, preco, destaque) VALUES ('Pokémon TCG - Combo De Booster Fogo Branco', 'Copag', 11, 'Coleção de 108 cartas Pokémon em formato padrão de 63mm x 88mm, incluindo cartas especiais dos Pokémon da região de Unova', '8+', '/imagens/brinquedos/Pokemon.png', 215.90, true);

--Eletrônicos--
INSERT INTO brinquedo (nome, marca, categoria_id, descricao, idade_ideal, imagem_principal, preco, destaque) VALUES ('Cachorro Robô Eletrônico', 'Robô Eletrônico', 12, 'Acessórios do produto: cachorro de stunt * 1, controle remoto * 1, carregador USB * 1, manual * 1', '6+', '/imagens/brinquedos/CachorroEletronico.png', 165.15, false);

--Natal--
INSERT INTO brinquedo (nome, marca, categoria_id, descricao, idade_ideal, imagem_principal, preco, destaque) VALUES ('CD O Natal do Bebê', 'Evan CDs', 13, 'Coleção de música infantis natalinas.', '3+', '/imagens/brinquedos/MusicaNatal.png', 15.90, false);

--Halloween--
INSERT INTO brinquedo (nome, marca, categoria_id, descricao, idade_ideal, imagem_principal, preco, destaque) VALUES ('Fantasia de Dinossauro Inflável', 'MT MENGTONG', 14, '‎ 8 x 19 x 16,99 cm; 458 g', '6+', '/imagens/brinquedos/FantasiaDinossauro.png', 216.90, false);


--Usuários--
INSERT INTO usuario (nome, username, senha, admin) VALUES ('Administrador', 'teste', '$2a$10$PGbBlRRc2YfnzSOXvh6PKe5GHzlJkY6UP2.LrPrv8suAmFfYykxdq', true);
INSERT INTO usuario (nome, username, senha, admin) VALUES ('Gabriel', 'gabriel', '$2a$10$PGbBlRRc2YfnzSOXvh6PKe5GHzlJkY6UP2.LrPrv8suAmFfYykxdq', false);