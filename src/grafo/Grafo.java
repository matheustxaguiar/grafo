package grafo;

import java.util.ArrayList;
import java.util.List;

public class Grafo<T> {
  private List<Vertice<T>> vertices;

  public Grafo() {
    vertices = new ArrayList<Vertice<T>>();
  }

  public List<Vertice<T>> getVertices() {
    return vertices;
  }

  public Vertice<T> adicionarVertice(T valor) {
    Vertice<T> novo = new Vertice<T>(valor);
    this.vertices.add(novo);
    return novo;
  }

  private Vertice<T> obterVertice(T valor) {
    Vertice<T> v;
    for (int i = 0; i < this.vertices.size(); i++) {
      v=this.vertices.get(i);
      if(v.getValor().equals(valor))
        return v;
    }
    return null;
  }

  public void adicionarAresta(T origem, T destino, float peso) {
    Vertice verticeOrigem, verticeDestino;
    verticeOrigem = obterVertice(origem);

    //Se não existir origem, adiciona-la ao grafo
    if(verticeOrigem==null)
      verticeOrigem=adicionarVertice(origem);

    verticeDestino = obterVertice(destino);

    //Se não existir destino, adiciona-lo ao grafo
    if(verticeDestino==null) {
      verticeDestino=adicionarVertice(destino);
    }

    //adicionar o destino a lista de destinos da origem e o peso
    verticeOrigem.adicinarDestino(new Aresta(verticeDestino, peso));
  }

  public void obterVizinhos(int identificador) {
    ArrayList<Aresta> arestas = this.vertices.get(identificador -1).getDestinos();
    
    Aresta aresta;
    for (int i = 0; i < arestas.size(); i++) {
      aresta = arestas.get(i);
      System.out.println(aresta.getDestino().getValor().toString() + "Peso:" + aresta.getPeso());
    }
  }

  public void buscaEmLargura(int identificador) {
    ArrayList<Vertice> marcados = new ArrayList<Vertice>();
    ArrayList<Vertice> fila = new ArrayList<Vertice>();

    //Arbitrariamente é pego um vertice para iniciar a busca
    Vertice atual = this.vertices.get(identificador -1);
  
    fila.add(atual);

    //Enquanto houver vertice na fila a ser processados na busca
    while(fila.size() > 0) {
      //transforma o primeiro da fila no atual, imprima, remove-o e o adicione nos marcados (ja visitados)
      atual = fila.get(0);
      fila.remove(0);
      marcados.add(atual);
      System.out.println(atual.getValor().toString());
      //Pega a lista de nós adjacentes
      ArrayList<Aresta> destinos = atual.getDestinos();
      Vertice proximo;

      //Para cada nó não marcado (não visitado) próximos ao nó atual do laço, adicione-o na fila
      for (int i = 0; i < destinos.size(); i++) {
        proximo=destinos.get(i).getDestino();
        if(!marcados.contains(proximo) && !fila.contains(proximo)){
          fila.add(proximo);
        }
      }
    }
  }
}