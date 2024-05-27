INSERT INTO Usuario (email, password, rol, activo, nombre)
SELECT 'test@unlam.edu.ar', 'test', 'ADMIN', true, 'Administrador'
WHERE NOT EXISTS (
        SELECT email FROM Usuario WHERE email = 'test@unlam.edu.ar'
    ) LIMIT 1;


insert into sintoma(idSintoma, nombre, itemTablero, descripcion, id_diagnostico) VALUES(	1	,'	Perdida de nafta	','	ItemFiltroGasolina	','	Una posible causa de pérdida de combustible podría ser un problema con el sello de la tapa del depósito de combustible. Si la tapa del depósito de combustible no está sellando correctamente, puede provocar una fuga de combustible.	',	1	);
insert into sintoma(idSintoma, nombre, itemTablero, descripcion, id_diagnostico) VALUES(	2	,'	Manguera rota	','	ItemFiltroGasolina	','	manguera rota no llegue la nafta	',	2	);
insert into sintoma(idSintoma, nombre, itemTablero, descripcion, id_diagnostico) VALUES(	3	,'	Sonda lambda	','	ItemMotor	','	sensor situado en el tubo de escape 	',	3	);
insert into sintoma(idSintoma, nombre, itemTablero, descripcion, id_diagnostico) VALUES(	4	,'	Catalizador	','	ItemMotor	','	Averia del componente Catalizador	',	4	);
insert into sintoma(idSintoma, nombre, itemTablero, descripcion, id_diagnostico) VALUES(	5	,'	Bujias	','	ItemMotor	','	Las bujías desgastadas o rotas provocan que la mezcla de aire y combustible no se queme de manera correcta, lo que eleva el consumo.	',	5	);
insert into sintoma(idSintoma, nombre, itemTablero, descripcion, id_diagnostico) VALUES(	6	,'	Tapa de desposito de combustible	','	ItemMotor	','	Si la tapa del deposito de combustible esta en mal estado o mal colocado puede provocar un aumento del consumo de combustible	',	6	);
insert into sintoma(idSintoma, nombre, itemTablero, descripcion, id_diagnostico) VALUES(	7	,'	Termostato	','	ItemMotor	','	Al fallar el termostato presenta un sintoma de sobrecalentamiento del motor, ademas se puede ver como sube la temperatura en el indicador	',	7	);
insert into sintoma(idSintoma, nombre, itemTablero, descripcion, id_diagnostico) VALUES(	8	,'	Bombinas de encendido	','	ItemMotor	','	Al estar funcionando erraticamente puede provocar una aceleracion mas lenta e incluso prodria provocar que el vehiculo se apague	',	8	);
insert into sintoma(idSintoma, nombre, itemTablero, descripcion, id_diagnostico) VALUES(	9	,'	Caudalímetro	','	ItemMotor	','	Cuando este funciona mal se presentan los sintomas de tirones al arrancar, falta de potencia, dificultades de arranque, humo negro del caño de escape, etc.	',	9	);
insert into sintoma(idSintoma, nombre, itemTablero, descripcion, id_diagnostico) VALUES(	10	,'	Sistema de control de emisiones	','	ItemMotor	','	Al fallar el motor se encuentra en emergencia totalmente ya que el vehiculo no supera las 2000 revoluciones por minuto	',	10	);
insert into sintoma(idSintoma, nombre, itemTablero, descripcion, id_diagnostico) VALUES(	11	,'	Fallo en el sensor de oxigeno	','	ItemMotor	','	Cuando este se encuentra en un fallo o mal estado el motor del coche puerde prestaciones	',	11	);
insert into sintoma(idSintoma, nombre, itemTablero, descripcion, id_diagnostico) VALUES(	12	,'	Cables de bujia rotos/mal estado	','	ItemMotor	','	Al encontrarse rotos o en mal estado estos la camara de combustion tiene mas mezcla de combustble/aire lo que provocara una especie de hipo o retraso en la aceleración 	',	12	);
insert into sintoma(idSintoma, nombre, itemTablero, descripcion, id_diagnostico) VALUES(	13	,'	Fallo de la valvula EGR	','	ItemMotor	','	 produce una considerable pérdida de potencia en el motor, tirones o dificultad de arranque en frío, además de mayor emisión de humos	',	13	);
insert into sintoma(idSintoma, nombre, itemTablero, descripcion, id_diagnostico) VALUES(	14	,'	fuga de vacio	','	ItemMotor	','	 entrada no deseada de aire no medido en el motor a través de una o más mangueras, juntas o componentes comprometidos. Este aire no medido puede perturbar la relación aire-combustible predeterminada del motor, lo que dificulta la combustión eficiente	',	14	);
insert into sintoma(idSintoma, nombre, itemTablero, descripcion, id_diagnostico) VALUES(	15	,'	Problemas con una alarma de mercado secundario	','	ItemMotor	','	Mala instalacion del sistema electronico de la alarma activa el check	',	15	);
insert into sintoma(idSintoma, nombre, itemTablero, descripcion, id_diagnostico) VALUES(	16	,'	Bobina de encendido en mal estado	','	ItemMotor	','	El vehículo presenta fallos en el encendido. Mala aceleración o pérdida de potencia.	',	16	);
insert into sintoma(idSintoma, nombre, itemTablero, descripcion, id_diagnostico) VALUES(	17	,'	Cambios en el recorrido del pedal de freno	','	ItemFreno	','	Liquido de frenos en mal estado	',	17	);
insert into sintoma(idSintoma, nombre, itemTablero, descripcion, id_diagnostico) VALUES(	18	,'	Vibracion en el pedal de freno	','	ItemFreno	','	Rodamiento de ruedas gastados	',	18	);
insert into sintoma(idSintoma, nombre, itemTablero, descripcion, id_diagnostico) VALUES(	19	,'	Ruido al frenar	','	ItemFreno	','	Pastillas de freno muy gastadas	',	19	);
insert into sintoma(idSintoma, nombre, itemTablero, descripcion, id_diagnostico) VALUES(	20	,'	 	','	ItemFreno	','	Pastillas de mala calidad	',	19	);
insert into sintoma(idSintoma, nombre, itemTablero, descripcion, id_diagnostico) VALUES(	21	,'	Acumulacion de suciedad en la pieza	','	ItemFiltroGasolina	','	Se acumulan sedimientos con el paso del tiempo	',	20	);
insert into sintoma(idSintoma, nombre, itemTablero, descripcion, id_diagnostico) VALUES(	22	,'	Deficiencia en la calidad del carburante	','	ItemFiltroGasolina	','	Combustible de baja calidad o contaminado	',	21	);
insert into sintoma(idSintoma, nombre, itemTablero, descripcion, id_diagnostico) VALUES(	23	,'	Mantenimiento y revision inadecuadas	','	ItemFiltroGasolina	','	 se puede acumular suciedad y obstruirse más fácilmente.	',	21	);
insert into sintoma(idSintoma, nombre, itemTablero, descripcion, id_diagnostico) VALUES(	24	,'	Uso excesivo de la pieza	','	ItemFiltroGasolina	','	No cambiar en forma periodica	',	22	);
insert into sintoma(idSintoma, nombre, itemTablero, descripcion, id_diagnostico) VALUES(	25	,'	Contaminación	','	ItemFiltroGasolina	','	Es contaminado por agua	',	23	);
insert into sintoma(idSintoma, nombre, itemTablero, descripcion, id_diagnostico) VALUES(	26	,'	Aumento de consumo en el combustible	','	ItemFiltroGasolina	','	Al estar averiado la mescla de aire y combustible no se hace bien	',	23	);
insert into sintoma(idSintoma, nombre, itemTablero, descripcion, id_diagnostico) VALUES(	27	,'	Perdida de potencia	','	ItemFiltroGasolina	','	Si esta mal el filtro, el flujo al combustible es menor	',	24	);
insert into sintoma(idSintoma, nombre, itemTablero, descripcion, id_diagnostico) VALUES(	28	,'	Bascular hacia un lado al conducir	','	ItemSuspension	','	Coche se inclina hacia el costado a causas de los muelles dañados	',	25	);
insert into sintoma(idSintoma, nombre, itemTablero, descripcion, id_diagnostico) VALUES(	29	,'	Sentir cada bache	','	ItemSuspension	','	Amortiguadores o muelles de suspensión fallando	',	26	);
insert into sintoma(idSintoma, nombre, itemTablero, descripcion, id_diagnostico) VALUES(	30	,'	Angulo mas bajo	','	ItemSuspension	','	Muelle gastado o dañado	',	23	);
insert into sintoma(idSintoma, nombre, itemTablero, descripcion, id_diagnostico) VALUES(	31	,'	Inclinarse hacia cualquier lado	','	ItemSuspension	','	Amortiguadores o muelles de suspensión fallando	',	26	);
insert into sintoma(idSintoma, nombre, itemTablero, descripcion, id_diagnostico) VALUES(	32	,'	Dificultad al girar	','	ItemSuspension	','	problema en la suspension o direccion	',	27	);
insert into sintoma(idSintoma, nombre, itemTablero, descripcion, id_diagnostico) VALUES(	33	,'	Amortiguadores aceitosos	','	ItemSuspension	','	Amortiguadores o muelles grasiento o aceitosos	',	28	);
insert into sintoma(idSintoma, nombre, itemTablero, descripcion, id_diagnostico) VALUES(	34	,'	Sensores defectuosos	','	ItemAirbag	','	Los sensores presentan daños o suciedad	',	29	);
insert into sintoma(idSintoma, nombre, itemTablero, descripcion, id_diagnostico) VALUES(	35	,'	Modulo de airbag humedo	','	ItemAirbag	','	Esto ocurre al exponerse hacia el agua provoca que se dañe o se active	',	30	);
insert into sintoma(idSintoma, nombre, itemTablero, descripcion, id_diagnostico) VALUES(	36	,'	Muelles del reloj del airbag desgastados	','	ItemAirbag	','	Desgaste	',	31	);
insert into sintoma(idSintoma, nombre, itemTablero, descripcion, id_diagnostico) VALUES(	37	,'	Airbag desactivados	','	ItemAirbag	','	Fallo en los componentes	',	32	);
insert into sintoma(idSintoma, nombre, itemTablero, descripcion, id_diagnostico) VALUES(	38	,'	Problemas electicos	','	ItemAirbag	','	Conexiones defectuosas o cables desgastados	',	32	);
insert into sintoma(idSintoma, nombre, itemTablero, descripcion, id_diagnostico) VALUES(	39	,'	Componente de airbag dañados	','	ItemAirbag	','	Propio airbag, cableado o cualquier componente aislado	',	33	);
insert into sintoma(idSintoma, nombre, itemTablero, descripcion, id_diagnostico) VALUES(	40	,'	Inclinarse hacia cualquier lado	','	ItemDireccion	','	Amortiguadores o muelles de suspensión fallando	',	26	);
insert into sintoma(idSintoma, nombre, itemTablero, descripcion, id_diagnostico) VALUES(	41	,'	Sobreviraje	','	ItemDireccion	','	Pierde traccion en curvas	',	34	);
insert into sintoma(idSintoma, nombre, itemTablero, descripcion, id_diagnostico) VALUES(	42	,'	Direccion asistida dura	','	ItemDireccion	','	Resulta dificil girar el volante	',	35	);
insert into sintoma(idSintoma, nombre, itemTablero, descripcion, id_diagnostico) VALUES(	43	,'	Direccion floja	','	ItemDireccion	','	Direccion demasiado suave	',	36	);
insert into sintoma(idSintoma, nombre, itemTablero, descripcion, id_diagnostico) VALUES(	44	,'	Sacudidas al volante	','	ItemDireccion	','	Saltos o sacudidas a intervalos irregulares	',	37	);
insert into sintoma(idSintoma, nombre, itemTablero, descripcion, id_diagnostico) VALUES(	45	,'	Vibracion al volante	','	ItemDireccion	','	a partir de 70km el volante vibra, lo cual dificulta mucho el manejo	',	38	);
insert into sintoma(idSintoma, nombre, itemTablero, descripcion, id_diagnostico) VALUES(	46	,'	Ruidos al girar el volante	','	ItemDireccion	','	ruidos o golpeteos al girar	',	39	);
insert into sintoma(idSintoma, nombre, itemTablero, descripcion, id_diagnostico) VALUES(	47	,'		','	ItemDireccion	','		',	39	);
insert into sintoma(idSintoma, nombre, itemTablero, descripcion, id_diagnostico) VALUES(	48	,'	Ruidos procedentes de la unidad de dirección asistida	','	ItemDireccion	','	quejido de la direccion cuando se gira	',	40	);
insert into sintoma(idSintoma, nombre, itemTablero, descripcion, id_diagnostico) VALUES(	49	,'	Olor a quemado	','	ItemEmbrague	','	Sobresfuerzo del embregue	',	41	);
insert into sintoma(idSintoma, nombre, itemTablero, descripcion, id_diagnostico) VALUES(	50	,'	El pedal del embrague no vuelve	','	ItemEmbrague	','	Muelle o plato del embregue roto	',	42	);
insert into sintoma(idSintoma, nombre, itemTablero, descripcion, id_diagnostico) VALUES(	51	,'	Averia en el bombin del embregue	','	ItemEmbrague	','	fallo hidraulico	',	40	);
insert into sintoma(idSintoma, nombre, itemTablero, descripcion, id_diagnostico) VALUES(	52	,'	Embregue suelto	','	ItemEmbrague	','	Sistema hidraulico tiene aire y no deja pasar al liquido de frenos	',	43	);
insert into sintoma(idSintoma, nombre, itemTablero, descripcion, id_diagnostico) VALUES(	53	,'	Pedal del embrague duro	','	ItemEmbrague	','	Final de su vida util	',	44	);
insert into sintoma(idSintoma, nombre, itemTablero, descripcion, id_diagnostico) VALUES(	54	,'	Suciedad en los sensores	','	ItemEstabilidad	','	Suciedad en los sensores de las ruedas	',	45	);
insert into sintoma(idSintoma, nombre, itemTablero, descripcion, id_diagnostico) VALUES(	55	,'	Fallo puntual del ESP grabado en la unidad de control	','	ItemEstabilidad	','		',	46	);
insert into sintoma(idSintoma, nombre, itemTablero, descripcion, id_diagnostico) VALUES(	56	,'	Testigo del esp por problemas en la bateria o el alternador	','	ItemEstabilidad	','		',	47	);
insert into sintoma(idSintoma, nombre, itemTablero, descripcion, id_diagnostico) VALUES(	57	,'	Problema en el sistema de emision de gases	','	ItemService	','		',	48	);
insert into sintoma(idSintoma, nombre, itemTablero, descripcion, id_diagnostico) VALUES(	58	,'	Fallas en el sistema de encendido	','	ItemService	','	No arranca el auto	',	47	);
insert into sintoma(idSintoma, nombre, itemTablero, descripcion, id_diagnostico) VALUES(	59	,'	Problemas en el sistema de inyeccion	','	ItemService	','	el sistema de inyeccion se encuentra roto o dañado	',	32	);
insert into sintoma(idSintoma, nombre, itemTablero, descripcion, id_diagnostico) VALUES(	60	,'	Problemas en el sistema electronico	','	ItemService	','	Conexiones defectuosas, cables desgastados	',	49	);
insert into sintoma(idSintoma, nombre, itemTablero, descripcion, id_diagnostico) VALUES(	61	,'	Fallas en el termostato	','	ItemService	','	termostato dañado provoca que el vehiculo no se pueda prender 	',	47	);
insert into sintoma(idSintoma, nombre, itemTablero, descripcion, id_diagnostico) VALUES(	62	,'	Problema con el convertidor catalítico	','	ItemService	','	complicacion en la conexión del convertidor	',	50	);
insert into sintoma(idSintoma, nombre, itemTablero, descripcion, id_diagnostico) VALUES(	63	,'	Obstrucción u otras fallas de válvula de purga	','	ItemService	','	fallas de la valvula de purga	',	51	);
insert into sintoma(idSintoma, nombre, itemTablero, descripcion, id_diagnostico) VALUES(	64	,'	Fallo del cuerpo del acelerador	','	ItemEPC	','	daños dentro del cuerpo del acelerador	',	47	);
insert into sintoma(idSintoma, nombre, itemTablero, descripcion, id_diagnostico) VALUES(	65	,'	Sensor ABS defectuoso	','	ItemEPC	','	ABS sucio o dañado	',	52	);
insert into sintoma(idSintoma, nombre, itemTablero, descripcion, id_diagnostico) VALUES(	66	,'	Interruptor del pedal de freno averiado	','	ItemEPC	','	Freno del pedal roto	',	47	);
insert into sintoma(idSintoma, nombre, itemTablero, descripcion, id_diagnostico) VALUES(	67	,'	Anillo de abs defectuoso	','	ItemEPC	','	Anillo del abs roto	',	53	);
insert into sintoma(idSintoma, nombre, itemTablero, descripcion, id_diagnostico) VALUES(	68	,'	Un sensor de presion de freno fallado	','	ItemEPC	','	sensores dañados	', 52);

Insert into drivedoctor.diagnostico(idDiagnostico, descripcion) VALUES(	1	,'	La recomendación para este caso es verificar el funcionamiento de las mangueras de gasolina, en caso de que estas esten bien puede ser el tanque que este roto.	');
Insert into drivedoctor.diagnostico(idDiagnostico, descripcion) VALUES(	2	,'	Para el auto, si tienes las herramientas suficientes remplaza la manguera	');
Insert into drivedoctor.diagnostico(idDiagnostico, descripcion) VALUES(	3	,'	En este caso lo mejor seria cambiar este componente y verificar si ningun otro componente se encuentra dañado como la rosca de la sondo de grasa	');
Insert into drivedoctor.diagnostico(idDiagnostico, descripcion) VALUES(	4	,'	En este caso el auto podria arreglarse apagando el auto un tiempo pero pronto volvera a perder potencia, lo recomendable es cambiar el catalizador	');
Insert into drivedoctor.diagnostico(idDiagnostico, descripcion) VALUES(	5	,'	En el caso de que las bujias se encuentras color crema y estan limpias y secas significa que estan en buen estado, de lo contrario estaria averiadas lo cual es recomendable cambiarlas	');
Insert into drivedoctor.diagnostico(idDiagnostico, descripcion) VALUES(	6	,'	Lo mejor seria cambiar esta tapa para evitar la fuga	');
Insert into drivedoctor.diagnostico(idDiagnostico, descripcion) VALUES(	7	,'	Aunque con una buena limpieza de esta pieza puede seguir andando lo recomendables es cambiarla	');
Insert into drivedoctor.diagnostico(idDiagnostico, descripcion) VALUES(	8	,'	Cambiar estas bobinas para un mejor funcionamiento del auto	');
Insert into drivedoctor.diagnostico(idDiagnostico, descripcion) VALUES(	9	,'	Este es imposible de reparar, asi que hay que sustituirlo por uno nuevo	');
Insert into drivedoctor.diagnostico(idDiagnostico, descripcion) VALUES(	10	,'	desarmar el dispositivo, extraer la tarjeta integrada y desempolvarla con un limpiador de dispositivos electrónicos. 	');
Insert into drivedoctor.diagnostico(idDiagnostico, descripcion) VALUES(	11	,'	Este es imposible de reparar, asi que hay que sustituirlo por uno nuevo	');
Insert into drivedoctor.diagnostico(idDiagnostico, descripcion) VALUES(	12	,'	Cambiar estos cables para un correcto funcionamiento	');
Insert into drivedoctor.diagnostico(idDiagnostico, descripcion) VALUES(	13	,'	Esto puede estropear el auto asi que es recomendable cambiarlo rapidamente	');
Insert into drivedoctor.diagnostico(idDiagnostico, descripcion) VALUES(	14	,'	Cambiar las mangueras	');
Insert into drivedoctor.diagnostico(idDiagnostico, descripcion) VALUES(	15	,'	Instalar adecuadamente o consultar con un profesional	');
Insert into drivedoctor.diagnostico(idDiagnostico, descripcion) VALUES(	16	,'	Esto puede deberse a diferentes cosas se recomienda visitar un profesional	');
Insert into drivedoctor.diagnostico(idDiagnostico, descripcion) VALUES(	17	,'	Sustituir este liquido por uno nuevo para evitar la contaminación	');
Insert into drivedoctor.diagnostico(idDiagnostico, descripcion) VALUES(	18	,'	Cambiar por neumaticos mas nuevos	');
Insert into drivedoctor.diagnostico(idDiagnostico, descripcion) VALUES(	19	,'	Para evitar incidentes cambiar urgente	');
Insert into drivedoctor.diagnostico(idDiagnostico, descripcion) VALUES(	19	,'	Para evitar incidentes cambiar urgente	');
Insert into drivedoctor.diagnostico(idDiagnostico, descripcion) VALUES(	20	,'	Limpiar la zona de forma muy prolija para que este filtro pueda funcionar o cambiar la pieza	');
Insert into drivedoctor.diagnostico(idDiagnostico, descripcion) VALUES(	21	,'	Lo recomendable es que cambies este filtro ya que se va a encontrar dañado a causa de el mal combustible	');
Insert into drivedoctor.diagnostico(idDiagnostico, descripcion) VALUES(	21	,'	Lo recomendable es que cambies este filtro ya que se va a encontrar dañado a causa de el mal combustible	');
Insert into drivedoctor.diagnostico(idDiagnostico, descripcion) VALUES(	22	,'	Realizar el mantenimiento diariamente para evitar que se dañen otras zonas	');
Insert into drivedoctor.diagnostico(idDiagnostico, descripcion) VALUES(	23	,'	Cambiar inmediatamente la pieza	');
Insert into drivedoctor.diagnostico(idDiagnostico, descripcion) VALUES(	23	,'	Cambiar inmediatamente la pieza	');
Insert into drivedoctor.diagnostico(idDiagnostico, descripcion) VALUES(	24	,'	Cambiar o limpiar la pieza para un mejor funcionamiento	');
Insert into drivedoctor.diagnostico(idDiagnostico, descripcion) VALUES(	25	,'	En este caso lo mejor seria cambiar los muelles o verificar si las ruedas del auto estan desbalanceadas	');
Insert into drivedoctor.diagnostico(idDiagnostico, descripcion) VALUES(	26	,'	Cambiar los amortiguadores o tambien podria ser el muelle pero en gran parte esto esta afectado a los amortiguadores	');
Insert into drivedoctor.diagnostico(idDiagnostico, descripcion) VALUES(	23	,'	Cambiar inmediatamente la pieza	');
Insert into drivedoctor.diagnostico(idDiagnostico, descripcion) VALUES(	26	,'	Cambiar los amortiguadores o tambien podria ser el muelle pero en gran parte esto esta afectado a los amortiguadores	');
Insert into drivedoctor.diagnostico(idDiagnostico, descripcion) VALUES(	27	,'	Lo mejor es sustituir los neumaticos que estan en mal estado y alinearlos en el taller	');
Insert into drivedoctor.diagnostico(idDiagnostico, descripcion) VALUES(	28	,'	En este caso es posible que esten perdiendo liquido, lo mas recomendable es llevarlo al taller para saber de donde pierde	');
Insert into drivedoctor.diagnostico(idDiagnostico, descripcion) VALUES(	29	,'	En el caso de qu esten sucios limpiarlos suavemente para no dañar el dispositivo con un trapo poco humedo, si esto no soluciona hay que cambiar la pieza.	');
Insert into drivedoctor.diagnostico(idDiagnostico, descripcion) VALUES(	30	,'	Cambiar el airbag ya que esta pieza al estar mojada no sirve mas	');
Insert into drivedoctor.diagnostico(idDiagnostico, descripcion) VALUES(	31	,'	Cambiar la pieza del reloj, lo recomendable es que lo haga un profesional para que no dañe el airbag	');
Insert into drivedoctor.diagnostico(idDiagnostico, descripcion) VALUES(	32	,'	Llevar a un electricista para comprobar el correcto funcionamiento, el profesional te diagnosticara si ese es el problema	');
Insert into drivedoctor.diagnostico(idDiagnostico, descripcion) VALUES(	32	,'	Llevar a un electricista para comprobar el correcto funcionamiento, el profesional te diagnosticara si ese es el problema	');
Insert into drivedoctor.diagnostico(idDiagnostico, descripcion) VALUES(	33	,'	Cambiar el airbag	');
Insert into drivedoctor.diagnostico(idDiagnostico, descripcion) VALUES(	26	,'	Cambiar los amortiguadores o tambien podria ser el muelle pero en gran parte esto esta afectado a los amortiguadores	');
Insert into drivedoctor.diagnostico(idDiagnostico, descripcion) VALUES(	34	,'	Para poder solucionarlo deberias levantar despacio el pie del acelerador y hacer una maniobra de contravolante.	');
Insert into drivedoctor.diagnostico(idDiagnostico, descripcion) VALUES(	35	,'	En primer caso inflar los neumaticos a la presion correcta, sino hay que ajustar la banda o revisar la alineacion delantera	');
Insert into drivedoctor.diagnostico(idDiagnostico, descripcion) VALUES(	36	,'	Ajustar la presion de los neumaticos, sino puede ser los amortiguadores o rotulas dañados, si estan dañados cambiarlos inmediatamente	');
Insert into drivedoctor.diagnostico(idDiagnostico, descripcion) VALUES(	37	,'	La barra de acoplamiento se ha desgastado, lo que provoca que se suelte y se desarrolla un juego excesivo, cambiar esta pieza	');
Insert into drivedoctor.diagnostico(idDiagnostico, descripcion) VALUES(	38	,'	Ajustar la presion de los neumaticos, sino cambiar las ruedas pueden estar muy dañadas y alinearlas	');
Insert into drivedoctor.diagnostico(idDiagnostico, descripcion) VALUES(	39	,'	Los posibles culpables de esto van a ser las articulaciones y rotulas de la suspension rota o desgastada	');
Insert into drivedoctor.diagnostico(idDiagnostico, descripcion) VALUES(	39	,'	Los posibles culpables de esto van a ser las articulaciones y rotulas de la suspension rota o desgastada	');
Insert into drivedoctor.diagnostico(idDiagnostico, descripcion) VALUES(	40	,'	Esto sucede cuando esta muy desgastado o roto entonces hay que cambiar esta pieza	');
Insert into drivedoctor.diagnostico(idDiagnostico, descripcion) VALUES(	41	,'	Imposible el uso del auto cambiar la pieza inmediatamente	');
Insert into drivedoctor.diagnostico(idDiagnostico, descripcion) VALUES(	42	,'	Cambiar la pieza del bombin ya que este dificulta el andar del auto	');
Insert into drivedoctor.diagnostico(idDiagnostico, descripcion) VALUES(	40	,'	Esto sucede cuando esta muy desgastado o roto entonces hay que cambiar esta pieza	');
Insert into drivedoctor.diagnostico(idDiagnostico, descripcion) VALUES(	43	,'	Su vida util llego al final hay que cambiar esta pieza	');
Insert into drivedoctor.diagnostico(idDiagnostico, descripcion) VALUES(	44	,'	Utilizaremos un liquido de limpieza como por ejemplo Eclipse,  que nos ayudara con la limpieza de este sector	');
Insert into drivedoctor.diagnostico(idDiagnostico, descripcion) VALUES(	45	,'	Esto indica que el sistema esta perdiendo traccion pero que el esp esta funcionando, se recomienda llevarlo al taller	');
Insert into drivedoctor.diagnostico(idDiagnostico, descripcion) VALUES(	46	,'	Esto puede ocurrir por la acumulacion de suciedad en los componentes, se recomienda hacer movimientos suaves del volante para la situacion se reconduzca	');
Insert into drivedoctor.diagnostico(idDiagnostico, descripcion) VALUES(	47	,'	Llevar a un taller de inmediato	');
Insert into drivedoctor.diagnostico(idDiagnostico, descripcion) VALUES(	48	,'	Si no arranca el auto es posible que la bateria este fallando, se puede cargar haciendo puente con otro auto pero se volvera a agotar. Se recomienda comprar una nueva	');
Insert into drivedoctor.diagnostico(idDiagnostico, descripcion) VALUES(	47	,'	Llevar a un taller de inmediato	');
Insert into drivedoctor.diagnostico(idDiagnostico, descripcion) VALUES(	32	,'	Llevar a un electricista para comprobar el correcto funcionamiento, el profesional te diagnosticara si ese es el problema	');
Insert into drivedoctor.diagnostico(idDiagnostico, descripcion) VALUES(	49	,'	Es posible que el sistema tenga un fusible quemado, deberias cambiar esta pieza o todo el termostato completo	');
Insert into drivedoctor.diagnostico(idDiagnostico, descripcion) VALUES(	47	,'	Llevar a un taller de inmediato	');
Insert into drivedoctor.diagnostico(idDiagnostico, descripcion) VALUES(	50	,'	Se recomienda cambiar la valvula, si el problema persiste llevar a un taller	');
Insert into drivedoctor.diagnostico(idDiagnostico, descripcion) VALUES(	51	,'	Se recomienda cambiar la pieza inmediatamente sino podria afectar al paso de combustible no quemado a traves del sistema de escape	');
Insert into drivedoctor.diagnostico(idDiagnostico, descripcion) VALUES(	47	,'	Llevar a un taller de inmediato	');
Insert into drivedoctor.diagnostico(idDiagnostico, descripcion) VALUES(	52	,'	Para evitar incidentes cambiar urgente	');
Insert into drivedoctor.diagnostico(idDiagnostico, descripcion) VALUES(	47	,'	Llevar a un taller de inmediato	');
Insert into drivedoctor.diagnostico(idDiagnostico, descripcion) VALUES(	53	,'	Podria ser un problema de piston de freno, el material de friccion o las pastillas de freno lo recomendable seria que lo vea un mecanico	');