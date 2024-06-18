-- Insertar datos en la tabla Usuario utilizando INSERT IGNORE para evitar inserciones duplicadas
INSERT IGNORE INTO Usuario (email, password, rol, activo, nombre)
VALUES
    ('test@unlam.edu.ar', 'test', 'ADMIN', true, 'Administrador');
-- Insertar datos en la tabla diagnostico utilizando INSERT IGNORE para evitar inserciones duplicadas
INSERT IGNORE INTO drivedoctor.diagnostico (descripcion) VALUES
                                                             ('La recomendación para este caso es verificar el funcionamiento de las mangueras de gasolina, en caso de que estas estén bien puede ser el tanque que esté roto.'),
                                                             ('Para el auto, si tienes las herramientas suficientes remplaza la manguera'),
                                                             ('En este caso lo mejor sería cambiar este componente y verificar si ningún otro componente se encuentra dañado como la rosca de la sonda de grasa'),
                                                             ('En este caso el auto podría arreglarse apagando el auto un tiempo pero pronto volverá a perder potencia, lo recomendable es cambiar el catalizador'),
                                                             ('En el caso de que las bujías se encuentren color crema y estén limpias y secas significa que están en buen estado, de lo contrario estarían averiadas lo cual es recomendable cambiarlas'),
                                                             ('Lo mejor sería cambiar esta tapa para evitar la fuga'),
                                                             ('Aunque con una buena limpieza de esta pieza puede seguir andando lo recomendable es cambiarla'),
                                                             ('Cambiar estas bobinas para un mejor funcionamiento del auto'),
                                                             ('Este es imposible de reparar, así que hay que sustituirlo por uno nuevo'),
                                                             ('Desarmar el dispositivo, extraer la tarjeta integrada y desempolvarla con un limpiador de dispositivos electrónicos.'),
                                                             ('Este es imposible de reparar, así que hay que sustituirlo por uno nuevo'),
                                                             ('Cambiar estos cables para un correcto funcionamiento'),
                                                             ('Esto puede estropear el auto así que es recomendable cambiarlo rápidamente'),
                                                             ('Cambiar las mangueras'),
                                                             ('Instalar adecuadamente o consultar con un profesional'),
                                                             ('Esto puede deberse a diferentes cosas se recomienda visitar un profesional'),
                                                             ('Sustituir este líquido por uno nuevo para evitar la contaminación'),
                                                             ('Cambiar por neumáticos más nuevos'),
                                                             ('Para evitar incidentes cambiar urgente'),
                                                             ('Limpiar la zona de forma muy prolija para que este filtro pueda funcionar o cambiar la pieza'),
                                                             ('Lo recomendable es que cambies este filtro ya que se va a encontrar dañado a causa del mal combustible'),
                                                             ('Realizar el mantenimiento diariamente para evitar que se dañen otras zonas'),
                                                             ('Cambiar inmediatamente la pieza'),
                                                             ('Cambiar o limpiar la pieza para un mejor funcionamiento'),
                                                             ('En este caso lo mejor sería cambiar los muelles o verificar si las ruedas del auto están desbalanceadas'),
                                                             ('Cambiar los amortiguadores o también podría ser el muelle pero en gran parte esto está afectado a los amortiguadores'),
                                                             ('Lo mejor es sustituir los neumáticos que están en mal estado y alinearlos en el taller'),
                                                             ('En este caso es posible que estén perdiendo líquido, lo más recomendable es llevarlo al taller para saber de dónde pierde'),
                                                             ('En el caso de que estén sucios limpiarlos suavemente para no dañar el dispositivo con un trapo poco húmedo, si esto no soluciona hay que cambiar la pieza.'),
                                                             ('Cambiar el airbag ya que esta pieza al estar mojada no sirve más'),
                                                             ('Cambiar la pieza del reloj, lo recomendable es que lo haga un profesional para que no dañe el airbag'),
                                                             ('Llevar a un electricista para comprobar el correcto funcionamiento, el profesional te diagnosticará si ese es el problema'),
                                                             ('Cambiar el airbag'),
                                                             ('Para poder solucionarlo deberías levantar despacio el pie del acelerador y hacer una maniobra de contravolante.'),
                                                             ('En primer caso inflar los neumáticos a la presión correcta, sino hay que ajustar la banda o revisar la alineación delantera'),
                                                             ('Ajustar la presión de los neumáticos, sino puede ser los amortiguadores o rótulas dañados, si están dañados cambiarlos inmediatamente'),
                                                             ('La barra de acoplamiento se ha desgastado, lo que provoca que se suelte y se desarrolla un juego excesivo, cambiar esta pieza'),
                                                             ('Ajustar la presión de los neumáticos, sino cambiar las ruedas pueden estar muy dañadas y alinearlas'),
                                                             ('Los posibles culpables de esto van a ser las articulaciones y rótulas de la suspensión rota o desgastada'),
                                                             ('Esto sucede cuando está muy desgastado o roto entonces hay que cambiar esta pieza'),
                                                             ('Imposible el uso del auto cambiar la pieza inmediatamente'),
                                                             ('Cambiar la pieza del bombín ya que este dificulta el andar del auto'),
                                                             ('Su vida útil llegó al final hay que cambiar esta pieza'),
                                                             ('Utilizaremos un líquido de limpieza como por ejemplo Eclipse, que nos ayudará con la limpieza de este sector'),
                                                             ('Esto indica que el sistema está perdiendo tracción pero que el esp está funcionando, se recomienda llevarlo al taller'),
                                                             ('Esto puede ocurrir por la acumulación de suciedad en los componentes, se recomienda hacer movimientos suaves del volante para la situación se reconduzca'),
                                                             ('Llevar a un taller de inmediato'),
                                                             ('Si no arranca el auto es posible que la batería esté fallando, se puede cargar haciendo puente con otro auto pero se volverá a agotar. Se recomienda comprar una nueva'),
                                                             ('Es posible que el sistema tenga un fusible quemado, deberías cambiar esta pieza o todo el termostato completo'),
                                                             ('Se recomienda cambiar la válvula, si el problema persiste llevar a un taller'),
                                                             ('Se recomienda cambiar la pieza inmediatamente sino podría afectar al paso de combustible no quemado a través del sistema de escape'),
                                                             ('Para evitar incidentes cambiar urgente'),
                                                             ('Podría ser un problema de pistón de freno, el material de fricción o las pastillas de freno lo recomendable sería que lo vea un mecánico');







Insert into drivedoctor.itemtablero(idItemTablero, nombre, descripcion) VALUES	(1,'	ItemFiltroGasolina	','	prueba	');
Insert into drivedoctor.itemtablero(idItemTablero, nombre, descripcion) VALUES	(2,'	ItemMotor	','	prueba1	');
Insert into drivedoctor.itemtablero(idItemTablero, nombre, descripcion) VALUES	(3,'	ItemFreno	','	prueba2	');
Insert into drivedoctor.itemtablero(idItemTablero, nombre, descripcion) VALUES	(4,'	ItemSuspension	','	prueba3	');
Insert into drivedoctor.itemtablero(idItemTablero, nombre, descripcion) VALUES	(5,'	ItemAirbag	','	prueba4	');
Insert into drivedoctor.itemtablero(idItemTablero, nombre, descripcion) VALUES	(6,'	ItemDireccion	','	prueba5	');
Insert into drivedoctor.itemtablero(idItemTablero, nombre, descripcion) VALUES	(7,'	ItemEmbrague	','	prueba6	');
Insert into drivedoctor.itemtablero(idItemTablero, nombre, descripcion) VALUES	(8,'	ItemEstabilidad	','	prueba7	');
Insert into drivedoctor.itemtablero(idItemTablero, nombre, descripcion) VALUES	(9,'	ItemService	','	prueba8	');
Insert into drivedoctor.itemtablero(idItemTablero, nombre, descripcion) VALUES	(10,'	ItemEPC	','	prueba9	');


insert into drivedoctor.sintoma(idSintoma, nombre, item_tablero_id, descripcion, id_diagnostico) VALUES(	1	,'	Perdida de nafta	',	1	,'	Una posible causa de pérdida de combustible podría ser un problema con el sello de la tapa del depósito de combustible. Si la tapa del depósito de combustible no está sellando correctamente, puede provocar una fuga de combustible.	',	1	);
insert into drivedoctor.sintoma(idSintoma, nombre, item_tablero_id, descripcion, id_diagnostico) VALUES(	2	,'	Manguera rota	',	1	,'	manguera rota no llegue la nafta	',	2	);
insert into drivedoctor.sintoma(idSintoma, nombre, item_tablero_id, descripcion, id_diagnostico) VALUES(	3	,'	Sonda lambda	',	2	,'	sensor situado en el tubo de escape 	',	3	);
insert into drivedoctor.sintoma(idSintoma, nombre, item_tablero_id, descripcion, id_diagnostico) VALUES(	4	,'	Catalizador	',	2	,'	Averia del componente Catalizador	',	4	);
insert into drivedoctor.sintoma(idSintoma, nombre, item_tablero_id, descripcion, id_diagnostico) VALUES(	5	,'	Bujias	',	2	,'	Las bujías desgastadas o rotas provocan que la mezcla de aire y combustible no se queme de manera correcta, lo que eleva el consumo.	',	5	);
insert into drivedoctor.sintoma(idSintoma, nombre, item_tablero_id, descripcion, id_diagnostico) VALUES(	6	,'	Tapa de desposito de combustible	',	2	,'	Si la tapa del deposito de combustible esta en mal estado o mal colocado puede provocar un aumento del consumo de combustible	',	6	);
insert into drivedoctor.sintoma(idSintoma, nombre, item_tablero_id, descripcion, id_diagnostico) VALUES(	7	,'	Termostato	',	2	,'	Al fallar el termostato presenta un sintoma de sobrecalentamiento del motor, ademas se puede ver como sube la temperatura en el indicador	',	7	);
insert into drivedoctor.sintoma(idSintoma, nombre, item_tablero_id, descripcion, id_diagnostico) VALUES(	8	,'	Bombinas de encendido	',	2	,'	Al estar funcionando erraticamente puede provocar una aceleracion mas lenta e incluso prodria provocar que el vehiculo se apague	',	8	);
insert into drivedoctor.sintoma(idSintoma, nombre, item_tablero_id, descripcion, id_diagnostico) VALUES(	9	,'	Caudalímetro	',	2	,'	Cuando este funciona mal se presentan los sintomas de tirones al arrancar, falta de potencia, dificultades de arranque, humo negro del caño de escape, etc.	',	9	);
insert into drivedoctor.sintoma(idSintoma, nombre, item_tablero_id, descripcion, id_diagnostico) VALUES(	10	,'	Sistema de control de emisiones	',	2	,'	Al fallar el motor se encuentra en emergencia totalmente ya que el vehiculo no supera las 2000 revoluciones por minuto	',	10	);
insert into drivedoctor.sintoma(idSintoma, nombre, item_tablero_id, descripcion, id_diagnostico) VALUES(	11	,'	Fallo en el sensor de oxigeno	',	2	,'	Cuando este se encuentra en un fallo o mal estado el motor del coche puerde prestaciones	',	11	);
insert into drivedoctor.sintoma(idSintoma, nombre, item_tablero_id, descripcion, id_diagnostico) VALUES(	12	,'	Cables de bujia rotos/mal estado	',	2	,'	Al encontrarse rotos o en mal estado estos la camara de combustion tiene mas mezcla de combustble/aire lo que provocara una especie de hipo o retraso en la aceleración 	',	12	);
insert into drivedoctor.sintoma(idSintoma, nombre, item_tablero_id, descripcion, id_diagnostico) VALUES(	13	,'	Fallo de la valvula EGR	',	2	,'	 produce una considerable pérdida de potencia en el motor, tirones o dificultad de arranque en frío, además de mayor emisión de humos	',	13	);
insert into drivedoctor.sintoma(idSintoma, nombre, item_tablero_id, descripcion, id_diagnostico) VALUES(	14	,'	fuga de vacio	',	2	,'	 entrada no deseada de aire no medido en el motor a través de una o más mangueras, juntas o componentes comprometidos. Este aire no medido puede perturbar la relación aire-combustible predeterminada del motor, lo que dificulta la combustión eficiente	',	14	);
insert into drivedoctor.sintoma(idSintoma, nombre, item_tablero_id, descripcion, id_diagnostico) VALUES(	15	,'	Problemas con una alarma de mercado secundario	',	2	,'	Mala instalacion del sistema electronico de la alarma activa el check	',	15	);
insert into drivedoctor.sintoma(idSintoma, nombre, item_tablero_id, descripcion, id_diagnostico) VALUES(	16	,'	Bobina de encendido en mal estado	',	2	,'	El vehículo presenta fallos en el encendido. Mala aceleración o pérdida de potencia.	',	16	);
insert into drivedoctor.sintoma(idSintoma, nombre, item_tablero_id, descripcion, id_diagnostico) VALUES(	17	,'	Cambios en el recorrido del pedal de freno	',	3	,'	Liquido de frenos en mal estado	',	17	);
insert into drivedoctor.sintoma(idSintoma, nombre, item_tablero_id, descripcion, id_diagnostico) VALUES(	18	,'	Vibracion en el pedal de freno	',	3	,'	Rodamiento de ruedas gastados	',	18	);
insert into drivedoctor.sintoma(idSintoma, nombre, item_tablero_id, descripcion, id_diagnostico) VALUES(	19	,'	Ruido al frenar	',	3	,'	Pastillas de freno muy gastadas	',	19	);
insert into drivedoctor.sintoma(idSintoma, nombre, item_tablero_id, descripcion, id_diagnostico) VALUES(	20	,'	 	',	3	,'	Pastillas de mala calidad	',	19	);
insert into drivedoctor.sintoma(idSintoma, nombre, item_tablero_id, descripcion, id_diagnostico) VALUES(	21	,'	Acumulacion de suciedad en la pieza	',	1	,'	Se acumulan sedimientos con el paso del tiempo	',	20	);
insert into drivedoctor.sintoma(idSintoma, nombre, item_tablero_id, descripcion, id_diagnostico) VALUES(	22	,'	Deficiencia en la calidad del carburante	',	1	,'	Combustible de baja calidad o contaminado	',	21	);
insert into drivedoctor.sintoma(idSintoma, nombre, item_tablero_id, descripcion, id_diagnostico) VALUES(	23	,'	Mantenimiento y revision inadecuadas	',	1	,'	 se puede acumular suciedad y obstruirse más fácilmente.	',	21	);
insert into drivedoctor.sintoma(idSintoma, nombre, item_tablero_id, descripcion, id_diagnostico) VALUES(	24	,'	Uso excesivo de la pieza	',	1	,'	No cambiar en forma periodica	',	22	);
insert into drivedoctor.sintoma(idSintoma, nombre, item_tablero_id, descripcion, id_diagnostico) VALUES(	25	,'	Contaminación	',	1	,'	Es contaminado por agua	',	23	);
insert into drivedoctor.sintoma(idSintoma, nombre, item_tablero_id, descripcion, id_diagnostico) VALUES(	26	,'	Aumento de consumo en el combustible	',	1	,'	Al estar averiado la mescla de aire y combustible no se hace bien	',	23	);
insert into drivedoctor.sintoma(idSintoma, nombre, item_tablero_id, descripcion, id_diagnostico) VALUES(	27	,'	Perdida de potencia	',	1	,'	Si esta mal el filtro, el flujo al combustible es menor	',	24	);
insert into drivedoctor.sintoma(idSintoma, nombre, item_tablero_id, descripcion, id_diagnostico) VALUES(	28	,'	Bascular hacia un lado al conducir	',	4	,'	Coche se inclina hacia el costado a causas de los muelles dañados	',	25	);
insert into drivedoctor.sintoma(idSintoma, nombre, item_tablero_id, descripcion, id_diagnostico) VALUES(	29	,'	Sentir cada bache	',	4	,'	Amortiguadores o muelles de suspensión fallando	',	26	);
insert into drivedoctor.sintoma(idSintoma, nombre, item_tablero_id, descripcion, id_diagnostico) VALUES(	30	,'	Angulo mas bajo	',	4	,'	Muelle gastado o dañado	',	23	);
insert into drivedoctor.sintoma(idSintoma, nombre, item_tablero_id, descripcion, id_diagnostico) VALUES(	31	,'	Inclinarse hacia cualquier lado	',	4	,'	Amortiguadores o muelles de suspensión fallando	',	26	);
insert into drivedoctor.sintoma(idSintoma, nombre, item_tablero_id, descripcion, id_diagnostico) VALUES(	32	,'	Dificultad al girar	',	4	,'	problema en la suspension o direccion	',	27	);
insert into drivedoctor.sintoma(idSintoma, nombre, item_tablero_id, descripcion, id_diagnostico) VALUES(	33	,'	Amortiguadores aceitosos	',	4	,'	Amortiguadores o muelles grasiento o aceitosos	',	28	);
insert into drivedoctor.sintoma(idSintoma, nombre, item_tablero_id, descripcion, id_diagnostico) VALUES(	34	,'	Sensores defectuosos	',	5	,'	Los sensores presentan daños o suciedad	',	29	);
insert into drivedoctor.sintoma(idSintoma, nombre, item_tablero_id, descripcion, id_diagnostico) VALUES(	35	,'	Modulo de airbag humedo	',	5	,'	Esto ocurre al exponerse hacia el agua provoca que se dañe o se active	',	30	);
insert into drivedoctor.sintoma(idSintoma, nombre, item_tablero_id, descripcion, id_diagnostico) VALUES(	36	,'	Muelles del reloj del airbag desgastados	',	5	,'	Desgaste	',	31	);
insert into drivedoctor.sintoma(idSintoma, nombre, item_tablero_id, descripcion, id_diagnostico) VALUES(	37	,'	Airbag desactivados	',	5	,'	Fallo en los componentes	',	32	);
insert into drivedoctor.sintoma(idSintoma, nombre, item_tablero_id, descripcion, id_diagnostico) VALUES(	38	,'	Problemas electicos	',	5	,'	Conexiones defectuosas o cables desgastados	',	32	);
insert into drivedoctor.sintoma(idSintoma, nombre, item_tablero_id, descripcion, id_diagnostico) VALUES(	39	,'	Componente de airbag dañados	',	5	,'	Propio airbag, cableado o cualquier componente aislado	',	33	);
insert into drivedoctor.sintoma(idSintoma, nombre, item_tablero_id, descripcion, id_diagnostico) VALUES(	40	,'	Inclinarse hacia cualquier lado	',	6	,'	Amortiguadores o muelles de suspensión fallando	',	26	);
insert into drivedoctor.sintoma(idSintoma, nombre, item_tablero_id, descripcion, id_diagnostico) VALUES(	41	,'	Sobreviraje	',	6	,'	Pierde traccion en curvas	',	34	);
insert into drivedoctor.sintoma(idSintoma, nombre, item_tablero_id, descripcion, id_diagnostico) VALUES(	42	,'	Direccion asistida dura	',	6	,'	Resulta dificil girar el volante	',	35	);
insert into drivedoctor.sintoma(idSintoma, nombre, item_tablero_id, descripcion, id_diagnostico) VALUES(	43	,'	Direccion floja	',	6	,'	Direccion demasiado suave	',	36	);
insert into drivedoctor.sintoma(idSintoma, nombre, item_tablero_id, descripcion, id_diagnostico) VALUES(	44	,'	Sacudidas al volante	',	6	,'	Saltos o sacudidas a intervalos irregulares	',	37	);
insert into drivedoctor.sintoma(idSintoma, nombre, item_tablero_id, descripcion, id_diagnostico) VALUES(	45	,'	Vibracion al volante	',	6	,'	a partir de 70km el volante vibra, lo cual dificulta mucho el manejo	',	38	);
insert into drivedoctor.sintoma(idSintoma, nombre, item_tablero_id, descripcion, id_diagnostico) VALUES(	46	,'	Ruidos al girar el volante	',	6	,'	ruidos o golpeteos al girar	',	39	);
insert into drivedoctor.sintoma(idSintoma, nombre, item_tablero_id, descripcion, id_diagnostico) VALUES(	47	,'		',	6	,'		',	39	);
insert into drivedoctor.sintoma(idSintoma, nombre, item_tablero_id, descripcion, id_diagnostico) VALUES(	48	,'	Ruidos procedentes de la unidad de dirección asistida	',	6	,'	quejido de la direccion cuando se gira	',	40	);
insert into drivedoctor.sintoma(idSintoma, nombre, item_tablero_id, descripcion, id_diagnostico) VALUES(	49	,'	Olor a quemado	',	7	,'	Sobresfuerzo del embregue	',	41	);
insert into drivedoctor.sintoma(idSintoma, nombre, item_tablero_id, descripcion, id_diagnostico) VALUES(	50	,'	El pedal del embrague no vuelve	',	7	,'	Muelle o plato del embregue roto	',	42	);
insert into drivedoctor.sintoma(idSintoma, nombre, item_tablero_id, descripcion, id_diagnostico) VALUES(	51	,'	Averia en el bombin del embregue	',	7	,'	fallo hidraulico	',	40	);
insert into drivedoctor.sintoma(idSintoma, nombre, item_tablero_id, descripcion, id_diagnostico) VALUES(	52	,'	Embregue suelto	',	7	,'	Sistema hidraulico tiene aire y no deja pasar al liquido de frenos	',	43	);
insert into drivedoctor.sintoma(idSintoma, nombre, item_tablero_id, descripcion, id_diagnostico) VALUES(	53	,'	Pedal del embrague duro	',	7	,'	Final de su vida util	',	44	);
insert into drivedoctor.sintoma(idSintoma, nombre, item_tablero_id, descripcion, id_diagnostico) VALUES(	54	,'	Suciedad en los sensores	',	8	,'	Suciedad en los sensores de las ruedas	',	45	);
insert into drivedoctor.sintoma(idSintoma, nombre, item_tablero_id, descripcion, id_diagnostico) VALUES(	55	,'	Fallo puntual del ESP grabado en la unidad de control	',	8	,'		',	46	);
insert into drivedoctor.sintoma(idSintoma, nombre, item_tablero_id, descripcion, id_diagnostico) VALUES(	56	,'	Testigo del esp por problemas en la bateria o el alternador	',	8	,'		',	47	);
insert into drivedoctor.sintoma(idSintoma, nombre, item_tablero_id, descripcion, id_diagnostico) VALUES(	57	,'	Problema en el sistema de emision de gases	',	9	,'		',	48	);
insert into drivedoctor.sintoma(idSintoma, nombre, item_tablero_id, descripcion, id_diagnostico) VALUES(	58	,'	Fallas en el sistema de encendido	',	9	,'	No arranca el auto	',	47	);
insert into drivedoctor.sintoma(idSintoma, nombre, item_tablero_id, descripcion, id_diagnostico) VALUES(	59	,'	Problemas en el sistema de inyeccion	',	9	,'	el sistema de inyeccion se encuentra roto o dañado	',	32	);
insert into drivedoctor.sintoma(idSintoma, nombre, item_tablero_id, descripcion, id_diagnostico) VALUES(	60	,'	Problemas en el sistema electronico	',	9	,'	Conexiones defectuosas, cables desgastados	',	49	);
insert into drivedoctor.sintoma(idSintoma, nombre, item_tablero_id, descripcion, id_diagnostico) VALUES(	61	,'	Fallas en el termostato	',	9	,'	termostato dañado provoca que el vehiculo no se pueda prender 	',	47	);
insert into drivedoctor.sintoma(idSintoma, nombre, item_tablero_id, descripcion, id_diagnostico) VALUES(	62	,'	Problema con el convertidor catalítico	',	9	,'	complicacion en la conexión del convertidor	',	50	);
insert into drivedoctor.sintoma(idSintoma, nombre, item_tablero_id, descripcion, id_diagnostico) VALUES(	63	,'	Obstrucción u otras fallas de válvula de purga	',	9	,'	fallas de la valvula de purga	',	51	);
insert into drivedoctor.sintoma(idSintoma, nombre, item_tablero_id, descripcion, id_diagnostico) VALUES(	64	,'	Fallo del cuerpo del acelerador	',	10	,'	daños dentro del cuerpo del acelerador	',	47	);
insert into drivedoctor.sintoma(idSintoma, nombre, item_tablero_id, descripcion, id_diagnostico) VALUES(	65	,'	Sensor ABS defectuoso	',	10	,'	ABS sucio o dañado	',	52	);
insert into drivedoctor.sintoma(idSintoma, nombre, item_tablero_id, descripcion, id_diagnostico) VALUES(	66	,'	Interruptor del pedal de freno averiado	',	10	,'	Freno del pedal roto	',	47	);
insert into drivedoctor.sintoma(idSintoma, nombre, item_tablero_id, descripcion, id_diagnostico) VALUES(	67	,'	Anillo de abs defectuoso	',	10	,'	Anillo del abs roto	',	53	);
insert into drivedoctor.sintoma(idSintoma, nombre, item_tablero_id, descripcion, id_diagnostico) VALUES(	68	,'	Un sensor de presion de freno fallado	',	10	,'	sensores dañados	', 53);

/*-- Insertar cada marca individualmente y capturar su ID inmediatamente después
INSERT IGNORE INTO drivedoctor.marca (nombre) VALUES ('Chevrolet');
SET @id_chevrolet = LAST_INSERT_ID();

INSERT IGNORE INTO drivedoctor.marca (nombre) VALUES ('Ford');
SET @id_ford = LAST_INSERT_ID();

INSERT IGNORE INTO drivedoctor.marca (nombre) VALUES ('Volkswagen');
SET @id_volkswagen = LAST_INSERT_ID();

INSERT IGNORE INTO drivedoctor.marca (nombre) VALUES ('Renault');
SET @id_renault = LAST_INSERT_ID();

INSERT IGNORE INTO drivedoctor.marca (nombre) VALUES ('Fiat');
SET @id_fiat = LAST_INSERT_ID();

INSERT IGNORE INTO drivedoctor.marca (nombre) VALUES ('Toyota');
SET @id_toyota = LAST_INSERT_ID();

INSERT IGNORE INTO drivedoctor.marca (nombre) VALUES ('Peugeot');
SET @id_peugeot = LAST_INSERT_ID();

INSERT IGNORE INTO drivedoctor.marca (nombre) VALUES ('Honda');
SET @id_honda = LAST_INSERT_ID();
INSERT IGNORE INTO drivedoctor.marca (nombre) VALUES ('Nissan');
SET @id_nissan = LAST_INSERT_ID();
INSERT IGNORE INTO drivedoctor.marca (nombre) VALUES ('Citroen');
SET @id_citroen = LAST_INSERT_ID();
INSERT IGNORE INTO drivedoctor.marca (nombre) VALUES ('Hyundai');
SET @id_hyundai = LAST_INSERT_ID();
INSERT IGNORE INTO drivedoctor.marca (nombre) VALUES ('Kia');
SET @id_kia = LAST_INSERT_ID();
INSERT IGNORE INTO drivedoctor.marca (nombre) VALUES ('Mazda');
SET @id_mazda = LAST_INSERT_ID();
INSERT IGNORE INTO drivedoctor.marca (nombre) VALUES ('Audi');
SET @id_audi = LAST_INSERT_ID();
INSERT IGNORE INTO drivedoctor.marca (nombre) VALUES ('BMW');
SET @id_bmw = LAST_INSERT_ID();
INSERT IGNORE INTO drivedoctor.marca (nombre) VALUES ('Mercedes-Benz');
SET @id_mercedes = LAST_INSERT_ID();
INSERT IGNORE INTO drivedoctor.marca (nombre) VALUES ('SEAT');
SET @id_seat = LAST_INSERT_ID();

-- INSERT MODELOS
INSERT IGNORE INTO drivedoctor.modelo (nombre, id_marca) VALUES
                                                     ( 'Corsa', @id_chevrolet),
                                                     ( 'Cruze', @id_chevrolet),
                                                     ( 'Onix', @id_chevrolet),
                                                     ( 'Tracker', @id_chevrolet),
                                                     ( 'Fiesta', @id_ford),
                                                     ( 'Focus', @id_ford),
                                                     ( 'Mustang', @id_ford),
                                                     ( 'Ranger', @id_ford),
                                                     ( 'Gol', @id_volkswagen),
                                                     ( 'Polo', @id_volkswagen),
                                                     ( 'Tiguan', @id_volkswagen),
                                                     ( 'Vento', @id_volkswagen),
                                                     ( 'Clio', @id_renault),
                                                     ( 'Duster', @id_renault),
                                                     ( 'Fluence', @id_renault),
                                                     ( 'Sandero', @id_renault),
                                                     ( 'Palio', @id_fiat),
                                                     ( 'Siena', @id_fiat),
                                                     ( 'Strada', @id_fiat),
                                                     ( 'Uno', @id_fiat),
                                                     ( 'Corolla', @id_toyota),
                                                     ( 'Hilux', @id_toyota),
                                                     ( 'RAV4', @id_toyota),
                                                     ( 'Yaris', @id_toyota),
                                                     ( '208', @id_peugeot),
                                                     ( '308', @id_peugeot),
                                                     ( '408', @id_peugeot),
                                                     ( 'Partner', @id_peugeot),
                                                     ( 'Civic', @id_honda),
                                                     ( 'CR-V', @id_honda),
                                                     ( 'Fit', @id_honda),
                                                     ( 'HR-V', @id_honda),
                                                     ( 'March', @id_nissan),
                                                     ( 'Sentra', @id_nissan),
                                                     ( 'Versa', @id_nissan),
                                                     ( 'X-Trail', @id_nissan),
                                                     ( 'C3', @id_citroen),
                                                     ( 'C4', @id_citroen),
                                                     ( 'C-Elysée', @id_citroen),
                                                     ( 'C4 Cactus', @id_citroen),
                                                     ( 'Sonata',  @id_hyundai),
                                                     ( 'Elantra',  @id_hyundai),
                                                     ( 'Tucson',  @id_hyundai),
                                                     ( 'Picanto', @id_kia),
                                                     ( 'Rio', @id_kia),
                                                     ( 'Seltos', @id_kia),
                                                     ('Sportage', @id_kia),
                                                     ( '3',  @id_mazda),
                                                     ( '6',  @id_mazda),
                                                     ( 'CX-3',  @id_mazda),
                                                     ( 'CX-5',  @id_mazda),
                                                     ( 'Qashqai', @id_nissan),
                                                     ( 'Juke', @id_nissan),
                                                     ( 'Micra', @id_nissan),
                                                     ( 'Leaf', @id_nissan),
                                                     ( 'A1',  @id_audi),
                                                     ( 'A3',  @id_audi),
                                                     ( 'A4',  @id_audi),
                                                     ( 'Q3',  @id_audi),
                                                     ( 'Serie 1', @id_bmw),
                                                     ( 'Serie 2', @id_bmw),
                                                     ( 'Serie 3', @id_bmw),
                                                     ( 'X1', @id_bmw),
                                                     ( 'Clase A', @id_mercedes),
                                                     ( 'Clase C', @id_mercedes),
                                                     ( 'Clase E', @id_mercedes),
                                                     ( 'GLA', @id_mercedes),
                                                     ( 'Megane', @id_renault),
                                                     ( 'Kadjar', @id_renault),
                                                     ( 'Koleos', @id_renault),
                                                     ( '500', @id_fiat),
                                                     ( 'Panda', @id_fiat),
                                                     ( 'Tipo', @id_fiat),
                                                     ( '500X', @id_fiat),
                                                     ( 'Leon',  @id_seat),
                                                     ( 'Ibiza',  @id_seat),
                                                     ( 'Arona',  @id_seat),
                                                     ( 'Ateca',  @id_seat);*/
