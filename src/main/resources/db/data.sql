--Senha 123
INSERT INTO USUARIO(id, nome,email,apelido,senha) VALUES(1, 'Marcelo','marcelo@gmail.com', 'admin', '$2a$10$hm0eYRoAK3r2Mf3O30dDmOxShNZTLyM8QahF7cc38SYnrJYroNnSm') ON CONFLICT DO NOTHING;

INSERT INTO PERFIL(usuario_id,perfis) VALUES(1,1) ON CONFLICT DO NOTHING;