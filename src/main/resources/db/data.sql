INSERT INTO USUARIO(id, nome,email,apelido,senha) VALUES(1, 'Marcelo','marcelo@gmail.com', 'admin', '123') ON CONFLICT DO NOTHING;

INSERT INTO PERFIL(usuario_id,perfis) VALUES(1,1) ON CONFLICT DO NOTHING;