package org.alterq.util;

import java.util.Vector;
import java.lang.Math;

public class CalculateRigths{
	public static final int REDUCCION_1 = 1; //Reduccion al 13 de 4 triples
	public static final int REDUCCION_2 = 2; //Reduccion al 13 de 7 dobles
	public static final int REDUCCION_3 = 3; //Reduccion al 13 de 3 dobles y 3 triples
	public static final int REDUCCION_4 = 4; //Reduccion al 12 de 6 dobles y 2 triples
	public static final int REDUCCION_5 = 5; //Reduccion al 12 de 8 triples
	public static final int REDUCCION_6 = 6; //Reduccion al 12 de 11 dobles
	
	private int numDoblesReducidos = 0;
	private int numTriplesReducidos = 0;
	
	private boolean [] vTriples;
	private boolean [] vDobles;
	
	private int [] vPosTripleReducido;
	private int [] vPosDobleReducido;
	
	/** Funcion calcularApuestasQuinielaDirecta
	 * @param String quiniela :quiniela Realiza para dicha jornada
	 * @return int salida: Devuelve el numero de apuestas de la quiniela
	 */
	public int calcularApuestasQuinielaDirecta (String quiniela)
	{
		int apuestas = 0;
		int triples = 0;
		int dobles = 0;
		
		
		for (int i=0;i<14;i++)
		{
			
			if (quiniela.charAt(i)=='7')
			{
				vTriples[i] = true;
				triples++;
			}
			else if ((quiniela.charAt(i)=='3') || ((quiniela.charAt(i)=='5')) || ((quiniela.charAt(i)=='6')))
			{
				
				vDobles[i] = true;
				dobles++;
			}
			else
			{
				vTriples[i] = false;
				vDobles[i] = false;
			}
		}

		if ((dobles==0)&&(triples==0))
			apuestas = 1;
		else if (dobles==0)
			apuestas = (int)Math.pow(3, triples);
		else if (triples==0)
			apuestas = (int)Math.pow(2, dobles);
		else
			apuestas = (int)(Math.pow(2, dobles) * Math.pow(3, triples));
			
		return apuestas;
	}
	public boolean pleno15EsMultiple(char pleno15)
	{
		switch (pleno15){
		case '3':
		case '5':
		case '6':
		case '7':
		case '9':
		case 'a':
		case 'b':
		case 'c':
		case 'd':
		case 'e':
		case 'f': return true;
					
		}
		return false;
	}
	
	public String[] Despliegue(String quinielaRealizada, String casillasReducidas, int tipoReduccion)
	{
		String [] despliegue;
		vTriples = new boolean[14];
		vPosTripleReducido = new int[14];

		vDobles = new boolean[14];
		vPosDobleReducido = new int[14];
		
		int contTriplesReducidos = 0;
		int contDoblesReducidos = 0;
		
		System.out.println("CalcularAciertos:Calcular(quinielaRealizada="+quinielaRealizada+", casillasReducidas="+casillasReducidas+", tipoReduccion="+tipoReduccion+")");
		
		try {
			//Analizamos el tipo de reduccion para saber el fichero xml a elegir
			Vector reducciones = LeerReduccion(tipoReduccion);
			String [] dobles = null;
			
			String [] triples = null;
			
			quinielaRealizada = quinielaRealizada.replace('4', '2');
			
			if (reducciones.elementAt(0) != null)
				dobles = (String[]) reducciones.elementAt(0);
			else
				dobles = new String[0];
			
			if (reducciones.elementAt(1) != null)
				triples = (String[]) reducciones.elementAt(1);
			else
				triples = new String[0];

			//Calculamos el numero de combinaciones de la reduccion
			int numCombinaciones = 0;
			if (dobles.length!=0)
				numCombinaciones = ((String)dobles[0]).length();
			else if (triples.length!=0)
				numCombinaciones = ((String)triples[0]).length();
			else//es una apuesta directa
				numCombinaciones = 1; // las apuestas directas el despliegue
				//numCombinaciones = calcularApuestasQuinielaDirecta(quinielaRealizada);
				

			//Analizamos las casillas reducidas
			despliegue = new String [numCombinaciones];

			//Analizamos la quiniela Realizada
			for (int i=0;i<14;i++)
			{
				vPosTripleReducido[i] = -1;
				vPosDobleReducido[i] = -1;
				
				if (quinielaRealizada.charAt(i)=='7')
				{
					vTriples[i] = true;
					if (casillasReducidas.charAt(i) == 'T')
					{
						vPosTripleReducido[i] = ++contTriplesReducidos;
					}
				}
				else if ((quinielaRealizada.charAt(i)=='3') || ((quinielaRealizada.charAt(i)=='5')) || ((quinielaRealizada.charAt(i)=='6')))
				{
					
					vDobles[i] = true;
					if (casillasReducidas.charAt(i) == 'D')
					{
						vPosDobleReducido[i] = ++contDoblesReducidos;
					}
					
				}
				else
				{
					vTriples[i] = false;
					vDobles[i] = false;
				}
			}
			
			//Montamos la base del despliegue de la reduccion duplicando la quiniela realizada tantas veces como combinaciones tenga la reduccion utilizada
			for (int i=0; i<numCombinaciones;i++)
			{
				//Duplicamos la quiniela realizada
				despliegue[i] = quinielaRealizada;
			}
			
			//Incorporamos la reduccion a la base anterior
			int orden = -1;
			for (int j=0;j<14;j++)
			{
				//Comprobamos si el elemento es una reduccion para desplegar el valor correspondiente de su reduccion
				if (esReducido(j))
				{
					if(esTriple(j))
					{
						orden = vPosTripleReducido[j];
						for (int i=0; i<numCombinaciones;i++)	
						{
							String aux = triples[orden-1];
							char car = (char)aux.charAt(i);
							
							StringBuffer sbDespliegue = new StringBuffer(despliegue[i]);
							sbDespliegue.setCharAt(j,car);// modificamos por el valor correspondiente de la reduccion.
							despliegue[i]=sbDespliegue.toString();
						}
					}
					
					if(esDoble(j))
					{
						orden = vPosDobleReducido[j];
						for (int i=0; i<numCombinaciones;i++)	
						{
							char car = (char)dobles[orden-1].charAt(i);
							char car2 = ' ';
							
							if (car=='1')
							{
								if (quinielaRealizada.charAt(j) == '3')
								{
									car2 = '1';
								}
								else if (quinielaRealizada.charAt(j) == '5')
								{
									car2 = '1';
								}
								else if (quinielaRealizada.charAt(j) == '6')
								{
									car2 = 'X';
								}
							}
							else if (car=='X')
							{
								if (quinielaRealizada.charAt(j) == '3')
								{
									car2 = 'X';
								}
								else if (quinielaRealizada.charAt(j) == '5')
								{
									car2 = '2';
								}
								else if (quinielaRealizada.charAt(j) == '6')
								{
									car2 = '2';
								}
							}
							StringBuffer sbDespliegue = new StringBuffer(despliegue[i]);
							sbDespliegue.setCharAt(j,car2); // modificamos por el valor correspondiente de la reduccion.
							despliegue[i]=sbDespliegue.toString();
						}
					}
				}
			}
			
			//Realizamos el despliegue de los dobles y triples no reducidos
			for (int j=0;j<14;j++)
			{
				if (!esReducido(j))
				{
					if(esTriple(j))
					{
						//tenemos que triplicar el despliegue actual con los tres signos del triple
						int apuestas = despliegue.length * 3;
						numCombinaciones *= 3;
						
						String [] nuevoDespliegue = new String[apuestas];
						for (int i=0; i<apuestas/3 ; i++)
						{
							//Primera parte del triple
							StringBuffer sbDespliegue1 = new StringBuffer(despliegue[i]);
							sbDespliegue1.setCharAt(j,'1');
							nuevoDespliegue[i*3]=sbDespliegue1.toString();
							
							//Segunda parte del triple
							StringBuffer sbDespliegueX = new StringBuffer(despliegue[i]);
							sbDespliegueX.setCharAt(j,'X');
							nuevoDespliegue[i*3 + 1]=sbDespliegueX.toString();

							//Primera parte del triple
							StringBuffer sbDespliegue2 = new StringBuffer(despliegue[i]);
							sbDespliegue2.setCharAt(j,'2');
							nuevoDespliegue[i*3 + 2]=sbDespliegue2.toString();
						}
						despliegue = new String[apuestas];
                        for (int i=0; i<apuestas ; i++){
                        	despliegue[i] = nuevoDespliegue[i];
                        }                
					}
					
					if (esDoble(j))
					{
						//tenemos que duplicar el despliegue actual con los dos signos del doble
						int apuestas = despliegue.length * 2;
						numCombinaciones *= 2;
						
						String [] nuevoDespliegue = new String[apuestas];
						
						char car1 = ' ';
						char car2 = ' ';
						
						if (quinielaRealizada.charAt(j) == '3')
						{
							car1 = '1';
							car2 = 'X';
						}
						else if (quinielaRealizada.charAt(j) == '5')
						{
							car1 = '1';
							car2 = '2';
						}
						else if (quinielaRealizada.charAt(j) == '6')
						{
							car1 = 'X';
							car2 = '2';
						}
						
						for (int i=0; i<apuestas/2 ; i++)
						{
							//Primera parte del doble
							StringBuffer sbDespliegue1 = new StringBuffer(despliegue[i]);
							sbDespliegue1.setCharAt(j,car1); // modificamos por el valor de la primera parte del doble
							nuevoDespliegue[i*2]=sbDespliegue1.toString();
							
							//Segunda parte del doble
							StringBuffer sbDespliegueX = new StringBuffer(despliegue[i]);
							sbDespliegueX.setCharAt(j,car2); // modificamos por el valor de la segunda parte del doble
							nuevoDespliegue[i*2 + 1]=sbDespliegueX.toString();
						}
						
						despliegue = new String[apuestas];
                        for (int i=0; i<apuestas ; i++){
                        	despliegue[i] = nuevoDespliegue[i];
                        }                

					}
				}
			}
			
			//gestionamos el despliegue para el pleno al 15
			for (int j=14;j<16;j++)
			{
				if (pleno15EsMultiple(quinielaRealizada.charAt(j)))
				{
					int apuestas = 0;
					char car1 = ' ';
					char car2 = ' ';
					char car3 = ' ';
					char car4 = ' ';
					int multi = 0;
					
					String [] nuevoDespliegue = new String[apuestas];
	
					if (quinielaRealizada.charAt(j) == '3')
					{
						multi = 2;
						
						car1 = '0';
						car2 = '1';
					}
					if (quinielaRealizada.charAt(j) == '5')
					{
						multi = 2;
						
						car1 = '0';
						car2 = '2';
					}
					if (quinielaRealizada.charAt(j) == '6')
					{
						multi = 2;
						
						car1 = '1';
						car2 = '2';
					}
					if (quinielaRealizada.charAt(j) == '9')
					{
						multi = 2;
						
						car1 = '0';
						car2 = 'M';
					}
					if (quinielaRealizada.charAt(j) == 'a')
					{
						multi = 2;
						
						car1 = '1';
						car2 = 'M';
					}
					if (quinielaRealizada.charAt(j) == 'c')
					{
						multi = 2;
						
						car1 = '2';
						car2 = 'M';
					}
					if (quinielaRealizada.charAt(j) == '7')
					{
						multi = 3;
						
						car1 = '0';
						car2 = '1';
						car3 = '2';
					}
					if (quinielaRealizada.charAt(j) == 'b')
					{
						multi = 3;
						
						car1 = '0';
						car2 = '2';
						car3 = 'M';
					}
					if (quinielaRealizada.charAt(j) == 'd')
					{
						multi = 3;
						
						car1 = '0';
						car2 = '1';
						car3 = 'M';
					}
	
					if (quinielaRealizada.charAt(j) == 'e')
					{
						multi = 3;
						
						car1 = '1';
						car2 = '2';
						car3 = 'M';
					}
					if (quinielaRealizada.charAt(j) == 'f')
					{
						multi = 4;
						
						car1 = '0';
						car2 = '1';
						car3 = '2';
						car4 = 'M';
					}
					
					apuestas = despliegue.length * multi;
					numCombinaciones *= multi;
					
					nuevoDespliegue = new String[apuestas];
					
					for (int i=0; i<apuestas/multi ; i++)
					{
						if (multi == 2)
						{
							//Primera parte
							StringBuffer sbDespliegue1 = new StringBuffer(despliegue[i]);
							sbDespliegue1.setCharAt(j,car1); // modificamos por el valor de la primera parte del doble
							nuevoDespliegue[i*multi]=sbDespliegue1.toString();
							
							//Segunda parte
							StringBuffer sbDespliegue2 = new StringBuffer(despliegue[i]);
							sbDespliegue2.setCharAt(j,car2); // modificamos por el valor de la segunda parte del doble
							nuevoDespliegue[i*multi + 1]=sbDespliegue2.toString();
						}else if (multi == 3)
						{
							//Primera parte
							StringBuffer sbDespliegue1 = new StringBuffer(despliegue[i]);
							sbDespliegue1.setCharAt(j,car1); // modificamos por el valor de la primera parte del doble
							nuevoDespliegue[i*multi]=sbDespliegue1.toString();
							
							//Segunda parte
							StringBuffer sbDespliegue2 = new StringBuffer(despliegue[i]);
							sbDespliegue2.setCharAt(j,car2); // modificamos por el valor de la segunda parte del doble
							nuevoDespliegue[i*multi + 1]=sbDespliegue2.toString();
	
							//Tercera parte
							StringBuffer sbDespliegue3 = new StringBuffer(despliegue[i]);
							sbDespliegue3.setCharAt(j,car3); // modificamos por el valor de la segunda parte del doble
							nuevoDespliegue[i*multi + 2]=sbDespliegue3.toString();
							
						}else if (multi ==4)
						{
							//Primera parte
							StringBuffer sbDespliegue1 = new StringBuffer(despliegue[i]);
							sbDespliegue1.setCharAt(j,car1); // modificamos por el valor de la primera parte del doble
							nuevoDespliegue[i*multi]=sbDespliegue1.toString();
							
							//Segunda parte
							StringBuffer sbDespliegue2 = new StringBuffer(despliegue[i]);
							sbDespliegue2.setCharAt(j,car2); // modificamos por el valor de la segunda parte del doble
							nuevoDespliegue[i*multi + 1]=sbDespliegue2.toString();
	
							//Tercera parte
							StringBuffer sbDespliegue3 = new StringBuffer(despliegue[i]);
							sbDespliegue3.setCharAt(j,car3); // modificamos por el valor de la segunda parte del doble
							nuevoDespliegue[i*multi + 2]=sbDespliegue3.toString();
							
							//Cuarta parte
							StringBuffer sbDespliegue4 = new StringBuffer(despliegue[i]);
							sbDespliegue4.setCharAt(j,car4); // modificamos por el valor de la segunda parte del doble
							nuevoDespliegue[i*multi + 3]=sbDespliegue4.toString();
						}
					}
					
					despliegue = new String[apuestas];
	                for (int i=0; i<apuestas ; i++){
	                	despliegue[i] = nuevoDespliegue[i];
	                }                
				}				
			}
		}catch (Exception ex) { 
			System.out.println("ERROR: " + ex.getMessage());
			return null;
		}
		
		return despliegue;
	}
	
	/** Funcion Calcular
	 * @param String quinielaResultado :quiniela Resultado de la jornada
	 * @param String quinielaRealizada :quiniela Realiza para dicha jornada
	 * @param String casillasReducidas: indica si la casilla esta reducida o no ('D' = doble reducido 'T' = triple reducido y 'N'= no reducida)
	 * @param int tipoReducion: indica el tipo de reduccion que se ha utilizado (reduccion 1 = 4 triples, reduccion 2 = 7 dobles, reduccion 3 = 3triples y 3 dobles, etc)
	 * @return Vector salida: Devuelve el numero de aciertos de 10, 11, 12, 13, 14 y 15 que ha obtenido la quinelaRealizada
	 * comparandola con la quinielaResultado y teniendo en cuenta el tipo de reduccion utilizado
	 */
	public int[] Calculate(String quinielaResultado, String quinielaRealizada, String casillasReducidas, int tipoReduccion){
		int salida[] = {0,0,0,0,0,0};
		String[] despliegue;
		System.out.println("CalcularAciertos:Calcular(quinielaResultado="+quinielaResultado+", quinielaRealizada="+quinielaRealizada+", casillasReducidas="+casillasReducidas+", tipoReduccion="+tipoReduccion+")");
		
		try {
			
			despliegue = Despliegue(quinielaRealizada, casillasReducidas, tipoReduccion);
			
			//Analizamos la quiniela Resultado de la Jornada
			int cont15 = 0;
			int cont14 = 0;
			int cont13 = 0;
			int cont12 = 0;
			int cont11 = 0;
			int cont10 = 0;
			
			for (int z=0;z<despliegue.length;z++){
				switch (Comparar(despliegue[z], quinielaResultado))
				{
				case 10:
					cont10++;
					break;
				case 11:
					cont11++;
					break;
				case 12:
					cont12++;
					break;
				case 13:
					cont13++;
					break;
				case 14:
					cont14++;
					break;
				case 15:
					cont15++;
					break;
				}
			}
			
			//Asignamos los valores al vector salida
			salida[0] = cont10;
			salida[1] = cont11;
			salida[2] = cont12;
			salida[3] = cont13;
			salida[4] = cont14;
			salida[5] = cont15;
			
			System.out.println("CalcularAciertos: RESULTADO - Aciertos de 15=" + cont15 +  " Aciertos de 14=" + cont14 +  " Aciertos de 13=" + cont13 +  " Aciertos de 12=" + cont12 +  " Aciertos de 11=" + cont11 +  " Aciertos de 10=" + cont10);			
		}catch (Exception ex) { 
			System.out.println("ERROR: " + ex.getMessage());
			return null;
		}
		return salida;
	}
	
	/** Funcion Calcular
	 * @param String q1 :primera quiniela a comparar
	 * @param String q2 :segunda quiniela a comparar
	 * @return int aciertos: devuelve el numero de coincidencias que tienen ambas quinielas
	 */
	private int Comparar(String q1, String q2)
	{
		int rdo = 0;
		int pleno15 = 0;
		if (q1.length()!= q2.length()) return -1;
		for (int i=0;i<q1.length();i++)
		{
			if (q1.charAt(i) == q2.charAt(i))
			{
				if (i==14)//El pleno al quince solo lo contabilizaremos si se han acertado los anteriores resultados
				{
					pleno15++;
				}else if (i==15){
					pleno15++;
					if((rdo==14) && (pleno15==2))
					{
						rdo++;
					}

				}
				else // para el resto de los casos aumentamos el contador de aciertos
				{
					rdo++;
				}
			}
		}
		return rdo;
	}

	/** Funcion LeerReduccionXML
	 * @param int tipoReducion: indica el tipo de reduccion que se ha utilizado (reduccion 1 = 4 triples, reduccion 2 = 7 dobles, reduccion 3 = 3triples y 3 dobles, etc)
	 * @return Vector salida: devuelve el vector con las combinaciones del tipo de reduccion
	 */
	private Vector LeerReduccion(int tipoReduccion)
	{
		Vector salida = new Vector();
		String [] dobles = null;
		String [] triples = null;
		
		switch (tipoReduccion)
		{
		case REDUCCION_1:
			numDoblesReducidos = 0;
			numTriplesReducidos = 4;
			triples = new String[numTriplesReducidos];
			triples[0]="111XXX222";
			triples[1]="1X21X21X2";
			triples[2]="1X2X2121X";
			triples[3]="1X221XX21";
			break;
		case REDUCCION_2:
			numDoblesReducidos = 7;
			numTriplesReducidos = 0;
			dobles = new String[numDoblesReducidos];
			dobles[0]="11XXXX11XX11XX11";
			dobles[1]="11XXXX1111XX11XX";
			dobles[2]="11XX11XXXX1111XX";
			dobles[3]="11XX11XX11XXXX11";
			dobles[4]="1X1XX1X11X1X1X1X";
			dobles[5]="1X1X1X1XX1X11X1X";
			dobles[6]="1X1X1X1X1X1XX1X1";
			break;
		case REDUCCION_3:
			numDoblesReducidos = 3;
			numTriplesReducidos = 3;
			dobles = new String[numDoblesReducidos];
			dobles[0]="111XXXXXX111XXX111111XXX";
			dobles[1]="111111XXXXXXXXXXXX111111";
			dobles[2]="111111XXXXXX111111XXXXXX";
			triples = new String[numTriplesReducidos];
			triples[0]="1X21X21X21X21X21X21X21X2";
			triples[1]="1X21X21X21X2X2121XX2121X";
			triples[2]="1X21X21X21X221XX2121XX21";
			break;
		case REDUCCION_4:
			numDoblesReducidos = 6;
			numTriplesReducidos = 2;
			dobles = new String[numDoblesReducidos];
			dobles[0]="111111111111111X1111111X1XXX11XXXXXXXX1X11X1XXXXXXXXXXXXXXX111XX";
			dobles[1]="1XXX11XX11XXX1111XXXXX11111X111XXX1XX1111111XXX1111XXXXX111XXXXX";
			dobles[2]="111X11X1X1XX111111XX1111X11XXX1XX11X11X1XXXX111XXXXXX111XXXXX1XX";
			dobles[3]="1111X11XXXXX11XX1X1XX1XX111111111XXX1XX11XXX1XX1XX1XX11X11X1XXX1";
			dobles[4]="X1X111X11XXX1XX111X1XX1XXXXX1X1X11XXX1X1111XXX1XX1XX111X11X11X11";
			dobles[5]="11111XXX11X1XXX111111XXXX1X1111XX11XXX1XXX1X11X11XX1X1XX1XXXXX11";
			triples = new String[numTriplesReducidos];
			triples[0]="X21X2111XXX222111X211XXXX21X21X21X21X21X21X2111111XXX222222X2221";
			triples[1]="XX2221X21X21X21X111X21X211XXX22211111122X21X2222221XXXXXXXX21X21";
			break;
		case REDUCCION_5:
			numDoblesReducidos = 0;
			numTriplesReducidos = 8;
			triples = new String[numTriplesReducidos];
			triples[0]="111111111111111111XXXXXXXXX222222222111111111XXXXXXXXX222222222XXXXXXXXX222222222";
			triples[1]="111111111XXXXXXXXX111111111222222222222222222XXXXXXXXX111111111222222222XXXXXXXXX";
			triples[2]="222222222111111111111111111111111111XXXXXXXXXXXXXXXXXXXXXXXXXXX222222222222222222";
			triples[3]="111111111XXXXXXXXX222222222111111111222222222111111111XXXXXXXXXXXXXXXXXX222222222";
			triples[4]="11X21X2X211X21X2X211X21X2X211X21X2X211X21X2X211X21X2X211X21X2X211X21X2X211X21X2X2";
			triples[5]="12X1X122X12X1X122X12X1X122X12X1X122X12X1X122X12X1X122X12X1X122X12X1X122X12X1X122X";
			triples[6]="X111222XXX111222XXX111222XXX111222XXX111222XXX111222XXX111222XXX111222XXX111222XX";
			triples[7]="121XX21X2121XX21X2121XX21X2121XX21X2121XX21X2121XX21X2121XX21X2121XX21X2121XX21X2";
			break;
		case REDUCCION_6:
			numDoblesReducidos = 11;
			numTriplesReducidos = 0;
			dobles = new String[numDoblesReducidos];

			dobles[0]= "XXXX11X11XX11X1XX1X1X11XX11XX1XX1111X11X1X1XX1XX11X1111XXX1111111X1XXXXXXX111XXXX1XX11X11X1X1XX1XXXX11X11XX11XX1X1X11X1XX11XX1XX1111";
			dobles[1]= "XX11XXX1X1X1X1XX11X11XXX1X11XX11111XXXX11X111X1X1X1X11X11X1X111X11XX1XXX1X1XX1XX1X1X1X1XXX1XX1111XXXXX11XX1X111XX1XX11X1X1X1X111XX11";
			dobles[2]= "X1XXXX11X1XX1X111X1XX1XX11X11XX11X11X1X1X1X1X1X1X11X11XX1111X111X1X1XXX1XXXX11XX1XX1X1X1X1X1X1X1XX1XX11XX1XX11X11X1XXX1X11X1XX1111X1";
			dobles[3]= "XX1X1X1XXX1X11X1X11XXX11XX11X111XX111X1XXXX111X11X11X1X1X1111XX111XXX11XXXX1X1X1XX1XX1XXX1111X1XXX11XXX1XX11XX111XX1X1XX1X111X1X1X11";
			dobles[4]= "X1X1X1XX1X1XXXX111XX1X11X1X11X1X11X11XXX11XX111XX1111X1X11XX11X111XXX1XX11XX1X1XXXX11XXX11XX111XX1XX1X1XX1X1XX1X11XXX1111X1X11X1X1X1";
			dobles[5]= "1XXXX11XXX11X11X1XXX11X11X1X11X1X1X1XX11X11X1X11X1X1X111X1X1X11X11XX1XX1X1X1XXX1X1X1XX1X1XX1XX11X1X1X1XX1X1XX1XX111X1XX1XX111XX1111X";
			dobles[6]= "1XX11XXX11XXX111XX11XXX111XX111XX11X11XXX111XX111XX11X111XX11X11X1X1XX1XX11XXX1XX11XXX11XXX111XX1XX11XXX11XXX111XX11XXX111XX111XX11X";
			dobles[7]= "111XXXXX11111XXXXX11111XXXXX11111XXX11111XXXXX11111XXX11111XXX111X1XXX111XXXXX111XXXXX11111XXXXX111XXXXX11111XXXXX11111XXXXX11111XXX";
			dobles[8]= "11111111XXXXXXXXXX1111111111XXXXXXXX1111111111XXXXXXXX11111111XXXX1111XXXXXXXX11111111XXXXXXXXXX11111111XXXXXXXXXX1111111111XXXXXXXX";
			dobles[9]= "111111111111111111XXXXXXXXXXXXXXXXXX111111111111111111XXXXXXXXXXXX111111111111XXXXXXXXXXXXXXXXXX111111111111111111XXXXXXXXXXXXXXXXXX";
			dobles[10]="111111111111111111111111111111111111XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX111111111111111111111111111111XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
			break;
		}
		
		salida.add(dobles);
		salida.add(triples);
		
		return salida;
		
	}

	/** Funcion esDoble
	 * @param int indice: indica si la posicion del indice pasado es un doble dentro del boleto
	 * @return boolean salida: devuelve true en caso que sea un doble el elemento del boleto
	 */
	private boolean esDoble(int indice)
	{
		return vDobles[indice];
	}
	
	private boolean esTriple(int indice)
	{
		return vTriples[indice];
	}

	private boolean esReducido(int indice)
	{
		return ((vPosDobleReducido[indice] != -1)||(vPosTripleReducido[indice] != -1));
	}
	
	
	private int getPosDoble (int indice)
	{
		for (int i = 0; i < 15; i++)
		{
			if (vPosDobleReducido[i] == indice)
				return i;
		}
		
		return -1;
	}
	
	private int getPosTriple (int indice)
	{
		for (int i = 0; i < 15; i++)
		{
			if (vPosTripleReducido[i] == indice)
				return i;
		}
		
		return -1;
	}
}