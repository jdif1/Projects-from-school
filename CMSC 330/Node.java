/*
Jeff DiFatta
CMSC 330
This class contains the data structure used to keep track of which panels are attached to which.
This is accomplished by storing their int value in a node and then having any subpanels added as child nodes.
Those child nodes can in turn have child nodes added to them and so on.
 */

import java.util.ArrayList;
import java.util.List;

public class Node<T>{
    private List<Node<T>> children = new ArrayList<>();
    private Node<T> parent = null;
    private final T data;

    public Node(T data){
        this.data = data;
    }

    public Node(T data, Node<T> parent){
        this.data = data;
        this.parent = parent;
    }

    public List<Node<T>> getChildren(){
        return children;
    }

    public void addChild(Node<T> child){
        child.parent = this;
        this.children.add(child);
    }

    public T getData(){
        return this.data;
    }

    public Node<T> getParent(){
        return this.parent;
    }

    public boolean isRoot(){
        return (this.parent == null);
    }
}