package text;

public class Tree
{
  private Node rootNode;
  
  public Tree() {
    rootNode = new Node("");
  }
}

class Node
{
  private String value;
  private Node[] children;

  Node(String value)
  {
    this.value = value;
    this.children = new Node[26];
  }
}
