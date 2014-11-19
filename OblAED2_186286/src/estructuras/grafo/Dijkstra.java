package estructuras.grafo;

public class Dijkstra { 
    private ArcoGrafo[][] mKilometros;
    private int ultimo [];// guarda desde el origen al destino el ultimo vertice 
						 // que se visito antes del destino
    private ArcoGrafo[] D; // guarda los costos minimos a cada vertice
    private boolean F[];// vertices visitados
    private int s; // s es el origen
    private int n; // n es la cantidad de nodos
    
    public Dijkstra(int s, GrafoMatriz g){
        n = g.cantNodos;
        this.s = s;
        mKilometros = g.matrizAdyacencia;
        ultimo = new int[n];
        D = new ArcoGrafo[n];
        F = new boolean[n];     
    }
    
    public void caminoMinimos(){
    // valores iniciales
        for (int i = 0; i < n; i++) {
            F[i] = false;//Inicializa los vertices como no visitados
            D[i] = mKilometros[s][i];//pesos directos desde el origen hasta todos los posibles destinos 
            ultimo[i] = s;
        }
        
        // para no tener que volver a visitar el origen:
        F[s] = true;
        D[s] = null;
        
        // Pasos para marcar los n-1 vértices
        for (int i = 0; i < n; i++) {
            //int v = minimo(); /* selecciona vértice no marcado  de menor distancia */ // TODO !!
            

           //F[v] = true;
            
            // actualiza distancia de vértices no marcados
            for (int w = 0; w < n; w++) {
               /* if (!F[w]) {
                    if ((D[v] + mKilometros[v][w]) < D[w]) {
                        D[w] = D[v] + mKilometros[v][w];
                        ultimo[w] = v;
                    }
                }*/
            }
        }
        /*for (int i = 0; i < n; i++) {
            //System.out.println("kilometros minimo a "+i+": "+D[i]);
        }*/
    }
    
    public int minimo(GrafoMatriz g) { // TODO !!
        //int mx = GrafoMatriz.inf;
        int v = 1;
        for (int j = 0; j < n; j++) {
        //    if (!F[j] && (D[j] <= mx)) {
        //        mx = D[j];
        //        v = j;
        //    }
        }
        return v;
    }
    
}
