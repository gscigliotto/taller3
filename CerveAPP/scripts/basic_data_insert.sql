USE [cerveAPP]

INSERT INTO [dbo].[usuarios]
           ([dni]
           ,[nombre]
           ,[apellido]
           ,[telefono]
           ,[mail]
		   ,[pass]
		   ,userId)
     VALUES
           ('30333280','Guillermo','Scigliotto','1540716602','gscigliotto@gmail.com','123456','ABwppHH-BZMuTrhlQgQAHUCXH2VwMSsGt0vw6OaeadiCvVo5Cpmj1nsfT3JESgJ74DxJoC0z6wmLOh9rVg')
GO
INSERT INTO [dbo].[gustos]
           ([nombre_gusto]
           ,[cantidad_disponible],precio_litro)
     VALUES
           ('IPA',1.0,20.1)
GO
INSERT INTO [dbo].[gustos]
           ([nombre_gusto]
           ,[cantidad_disponible],precio_litro)
     VALUES
           ('Honey',1.0,23.76)
GO

INSERT INTO [dbo].[gustos]
           ([nombre_gusto]
           ,[cantidad_disponible],precio_litro)
     VALUES
           ('PORTER',1.0,24.55)
GO
