#Running the project Quid pro Quod

# Introduction #

You must have a PostgreSQL 8.x installed on your computer.



# Details #

Create a database and edit the **hibernate.cfg.xml** setting this properties with correct values of your DB:<br />
<br />
**_"hibernate.connection.password"_**<br />
**_"hibernate.connection.url"_**<br />
**_"hibernate.connection.username"_**<br />

## Scripts SQL ##


**Table Role**
<pre>
INSERT INTO role (id, admin, nome) VALUES (1, true, 'ADMIN');<br>
INSERT INTO role (id, admin, nome) VALUES (2, false, 'USER');<br>
INSERT INTO role (id, admin, nome) VALUES (3, true, 'MODERADOR');<br>
</pre>

**Table Usuario**
<pre>
insert into usuario (id, login, senha, id_role, bloqueado) values (1, 'admin', '21232f297a57a5a743894a0e4a801fc3',1, false);<br>
</pre>