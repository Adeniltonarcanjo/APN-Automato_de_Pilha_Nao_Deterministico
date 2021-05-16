

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author Dedei
 */
class Main {
         
   
 
        
   public static void main(String[] args) {
       
        
              
        Stack<Dados_pilha> pilha = new Stack<Dados_pilha>();     
        HashMap <String,ArrayList<Dados>> dicionario= new HashMap <>();
        // cada 
        HashMap<String, Integer> finais= new HashMap <>();
        HashMap<String, String> dicPilha= new HashMap <>();
             
        Scanner e= new Scanner (System.in);
        
        String[]  oc;
     
        String tripla;
        String estados;
        String alfabeto;
        String alfabetop;
        String estado_inicial;
        String estado_final;
        String[] estados_finais;     
        String[] palavras;
       
        int t,z,y=0;
        boolean contem;
             
        estados=e.nextLine();
        alfabeto=e.nextLine();
        alfabetop=e.nextLine();
        t=Integer.parseInt(e.nextLine());
        
        
      while(y<t){     // Esse While Armazena os estados que o automato esta concatenado com a palavra que ele pode consumir como chave
                      // cada chave tem como conteudo um array de estados possiveis 
       z=0;   
           
        tripla=e.nextLine();   // essa parte do código pega as entradas com o estado de origem, o caracter a ser lido, o caracter a ser desempilhado da pilha , o estado de destino e a palavra a ser empilhada
        
        oc=tripla.split(" "); 
        String  chave=(oc[z].concat(oc[z+1])); // concatena o estado de origem e o caracter a ser lido e cria uma string chave 
        Dados dados= new Dados(); // cria um objeto dadaos 
        
    if (!dicionario.containsKey(chave)){  // caso não contenha ainda essa chave 
         ArrayList<Dados> estado = new ArrayList<>();
         dados.consumo=oc[z+2];
         dados.empilha=oc[z+4];
         dados.estadoproximo=oc[z+3];
         estado.add(dados);
         dicionario.put(chave,estado);
                    
          
     }else {
         dados.consumo=oc[z+2];
         dados.empilha=oc[z+4];
         dados.estadoproximo=oc[z+3];
          dicionario.get(chave).add(dados);
          
       }
       
  
       y++;
       
      }
      
     estado_inicial=e.nextLine();
     estado_final=e.nextLine();
     estados_finais=estado_final.split(" ");
       
       for(int i=0;i<estados_finais.length;i++){  // Armazena os posiveis estados finais em uma tabela HASH
           finais.put(estados_finais[i], i);
       }
       
       
       
    
 String palavra;
 palavra=e.nextLine();
 palavras=palavra.split(" ");
 //----------------------------------------------------------------------------------------------------------------------
    // Objeto criado para armazenar o estado em que esta o automato e o restante da palavra a ser consumida 
 String p;
 
 for(int j=0;j<palavras.length;j++){ 
       
    Dados_pilha dadosp=new Dados_pilha(estado_inicial,palavras[j]);// o automato vai percorrer cada palavra 
    pilha.add(dadosp);
        
  contem=false;  
   long tempoi1= System.currentTimeMillis();
  while (pilha.size()>0){
       Dados_pilha a;
       a=pilha.pop();

       
       if (dicionario.containsKey(a.estadoatual.concat("*") )){ // Se o caracter a ser consumido é * 
               for(Dados da : dicionario.get(a.estadoatual.concat("*"))){                  
                Dados_pilha dadosp1=new Dados_pilha();
               dadosp1.palavra=a.palavra;
               dadosp1.estadoatual=da.estadoproximo;
                 for (String pi: a.pilha_c){
                      
                    dadosp1.pilha_c.push(pi);
                     
                  }
                 if (!pilha.contains(dadosp1)){
                     
                  pilha.push(dadosp1);
                 }
               }
              }  
       
       
       
                
      
       if((a.palavra.isEmpty() && (a.pilha_c.isEmpty()))){                  
           if(finais.containsKey(a.estadoatual)){
               contem=true;                                 
               pilha.clear();
              
               
           }
              
           
       }else{                                                
        
         if (a.palavra.length()>0){ 
                   
          
         p=a.palavra.substring(1, a.palavra.length());   
         // pega o restante da palavra a ser consumida 
         String cha=(a.estadoatual.concat(a.palavra.substring(0, 1)));
      //   String chav=(a.estado.concat("*"));
         
                
                                                             
     if(dicionario.containsKey(cha) ){                       
                     
         for(Dados u : dicionario.get(cha)) {   
                             
                             
            Dados_pilha dados3=new  Dados_pilha ();
            dados3.palavra=p;
            dados3.estadoatual=u.estadoproximo;
                            
              
                  for (String pi: a.pilha_c){
                      
                    dados3.pilha_c.push(pi);
                     
                  }
                             
              
                                                          
        if ((u.consumo.equalsIgnoreCase("*")) && (!u.empilha.equalsIgnoreCase("*"))){
                      
                          dados3.pilha_c.push(u.empilha);
                           pilha.push(dados3);
            
             } 
       else if ((!u.consumo.equalsIgnoreCase("*")) && (!u.empilha.equalsIgnoreCase("*"))){
                  if (u.consumo.equals(dados3.pilha_c.peek())){
                      
                    dados3.pilha_c.pop();
                    dados3.pilha_c.push(u.empilha);
                        pilha.push(dados3);
                      
                 }
             }else if((!u.consumo.equalsIgnoreCase("*")) && (u.empilha.equalsIgnoreCase("*"))) {
                 
                 
                 if (!dados3.pilha_c.isEmpty()){
                  if (u.consumo.equals(dados3.pilha_c.peek())){
                      
                    dados3.pilha_c.pop();
                   
                    pilha.push(dados3);
                  }
                    
                 }
             }
             else {
                      pilha.push(dados3);
                     
           }
                                          
}        

     
     }
            
     
     
       }
         
             
       }   
  
   }  
   long tempoi2= System.currentTimeMillis();    
   long tempototal= tempoi2-tempoi1;     
   if(contem){
       System.out.println("S "+ (tempototal));
       
   }else {
       System.out.println("N "+ (tempototal));
       
   }
     
  
     }
 
   
  // --------------------------------------------------------------------
   

    } 
    
}
   class Dados_pilha {  //
     
     String estadoatual;
     String palavra;
     Stack<String> pilha_c= new Stack<>(); 

     Dados_pilha(String estado, String palavra) {
        this.estadoatual = estado;
        this.palavra = palavra;
        this.pilha_c.clear();
        }
    
        Dados_pilha() {
      
        }

 }  
  class Dados {  

       
   String estadoproximo;
   String consumo;
   String empilha;
     
           
     
 }  
 