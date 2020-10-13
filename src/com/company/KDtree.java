package com.company;

import java.util.Scanner;

public class KDtree {

    public int[] dataPoints = new int[Main.k];
    public void ConstructTree(){
        Node root=null;
        Scanner cin = new Scanner(System.in);

        System.out.println("EnterRoots");
        for(int i=0;i<Main.k;i++){
            dataPoints[i]=cin.nextInt();
        }
        root=insertNode(root,dataPoints,0);


        while (true){

            int n;
            System.out.println("!. insert 2. search 0.Stop");
            n=cin.nextInt();
            if(n==1){

                for(int i=0;i<Main.k;i++){
                    dataPoints[i]=cin.nextInt();
                }
                root=insertNode(root,dataPoints,0);

                print(root,"Root",0);

            }
            else if(n==2){

                System.out.println("Enter serach point");
                int[] p=new int[Main.k];
                for (int i=0;i<Main.k;i++){
                    p[i]=cin.nextInt();
                }
                if(search(root, p,0)){
                        System.out.println("Point Found");
                }
            }

            else break;

        }
    }

    public Node insertNode(Node root, int[] dataPoints,int depth){

        if(root==null){
            return getNode(dataPoints);
        }

        int cd = depth % Main.k;

        if (dataPoints[cd] < (root.values[cd])){
            root.left  = insertNode(root.left, dataPoints, depth+1);

        }
        else{
            root.right = insertNode(root.right, dataPoints, depth+1);
        }


        return root;
    }

    public Node getNode(int[] dataPoints){
        Node newNode= new Node();

        for(int i=0;i<Main.k;i++){
            newNode.values[i]=dataPoints[i];
        }

        newNode.left=null;
        newNode.right=null;

        return newNode;

    }

    public boolean checkPoint(int[] rootPoint, int[] dataPoints){

        for (int i = 0; i < Main.k; ++i){

            if (rootPoint[i] != dataPoints[i])
                return false;

        }
        return true;
    }

    public boolean search(Node root,int[] point,int depth){

        if (root==null){
            return false;
        }
        if (checkPoint(root.values, point)){
            System.out.println(depth);
            return true;
        }


        int n = depth % Main.k;

        if (point[n] < root.values[n]){
            return search(root.left, point, depth + 1);
        }

        return search(root.right, point, depth + 1);

    }

    public void print(Node root,String branch, int depth){
        if(root==null) return;
        else {
            System.out.println(branch +" at depth" +depth);
            for(int i=0;i<Main.k;i++){
                System.out.print("  "+root.values[i]);
            }
            System.out.println();

            print(root.left, "left",depth+1);
            print(root.right, "right",depth+1);
        }
    }
}
