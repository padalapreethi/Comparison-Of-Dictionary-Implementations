 import java.io.*;
import java.util.*;
import java.lang.*;



class TrieNode
{

	char value;
	HashMap<Character,TrieNode> children;
	//boolean cisLeaf;
	int RunningNumber;

	public TrieNode(char ch)
	{
		System.out.println("Trie Node");
	value=ch;
	children=new HashMap<>();
	//cisLeaf=false;
	RunningNumber=0;
	}

	HashMap<Character,TrieNode> getChildren()
	{	
	return children;
	}

	char getValue()
	{
	return value;
	}
	
	int getRunningNumber()
	{
	return RunningNumber; 
	}
}


class SuffixTrie
{
	public int r;
	public TrieNode Sroot;

	public SuffixTrie()
	{
				System.out.println("Suffix Trie ");

	Sroot=new TrieNode((char)0);
	r=1;
	}

	TrieNode getSuffixRoot()
	{
		return Sroot;
	}


	void insertSuffix(String suff)
	{
				System.out.println("insert Suffix Trie Node");

	String SuffRev=new StringBuffer(suff).reverse().toString();
	int length=SuffRev.length();
	TrieNode currentNode=Sroot;
	for(int level=0;level<length;level++)
	{
	HashMap<Character,TrieNode>child=currentNode.getChildren();
	char ch =SuffRev.charAt(level);
	if(child.containsKey(ch))
	{
	currentNode=child.get(ch);
	}
	else	
	{
	TrieNode temp=new TrieNode(ch);
	child.put(ch,temp);
	currentNode=temp;
	}
	}
	currentNode.RunningNumber=r++;
			//System.out.println("character "+currentNode.value+"          R No"+currentNode.RunningNumber);

	}

}




class WordTrie 
{
public int length,runNo;
public TrieNode wRoot;
public WordTrie()
{
			System.out.println("word Trie Node");

wRoot=new TrieNode((char)0);
}


void insertWord(String word,TrieNode s1root)
{
			System.out.println("insert word Trie Node");

int val[]=iscontainsSuffix(word,s1root);
length=val[1];
runNo=val[0];
		System.out.println("Suffix lengthh   "+length+"    Running Number    "+runNo);
int prefixlength=word.length()-length;
 String prefixword=word.substring(0,prefixlength);
TrieNode currentNode=wRoot;
	for(int level=0;level<prefixlength;level++)
	{
	HashMap<Character,TrieNode>child=currentNode.getChildren();
	char ch=prefixword.charAt(level);
	if(child.containsKey(ch))		
	{
	currentNode=child.get(ch);
	}
	else
	{
	TrieNode temp=new TrieNode(ch);
	child.put(ch,temp);
	currentNode=temp;
	}
	System.out.println("              character  "+ch+"      Running Number       "+runNo);
	}
//if(length==word.length())
//{
//currentNode.RunningNumber=-1;
//}
if(runNo>0)
{
currentNode.RunningNumber=runNo;
}
else
	currentNode.RunningNumber=-1;
	
}
//end of insert word


//checking for suffix is existed or  not;
int[] iscontainsSuffix(String word,TrieNode s1root)
{
			System.out.println("is contains suffix Node");

String revString= new StringBuffer(word).reverse().toString();
int length1=word.length();
int arr1[]={0,0};
	System.out.println("given word    "+word+"        reverse of word    "+revString);
TrieNode currentNode=s1root;
for(int level=0;level<length1;level++)
{
	
		System.out.println();
	System.out.println(revString.charAt(level));
		System.out.println();
HashMap<Character,TrieNode> child=currentNode.getChildren();
char ch=revString.charAt(level);
if(child.containsKey(ch))
{
currentNode=child.get(ch);

//System.out.println("is Contains  Running character      "+ch+"         R.No"+currentNode.RunningNumber);
if(currentNode.RunningNumber>0)
{
int arr[]={currentNode.RunningNumber,level+1};
return arr;
}
}
else{
	break;
}
}

return arr1;
}

Boolean Search(String word,TrieNode s1root)
 {
	 		System.out.println("search word ");

 int arr[]=iscontainsSuffix(word,s1root);
 length=arr[1];
 runNo=arr[0];
 		System.out.println("\n Suffix length   "+length+"      run no  "+runNo+"\n");

 int prefixlength=word.length()-length;
 String prefixword=word.substring(0,prefixlength);
 TrieNode currentNode=wRoot;
 for(int level=0;level<prefixlength;level++)
  {
    HashMap<Character,TrieNode>child=currentNode.getChildren();
    char ch=prefixword.charAt(level);
    if(child.containsKey(ch))
     {
        currentNode=child.get(ch);
		System.out.println("\t"+ch+"\t Running Numner="+currentNode.RunningNumber);
        if(currentNode.RunningNumber>0 && runNo>0)
        return true;
    else 
	if(currentNode.RunningNumber==-1 && prefixlength==word.length())
        return true;
       }
  }
  return false;
}

}
//end if wordTrie class


class SST
{
public static void main(String args[])
{
String suffixes[]={"ing","able","es","tion","ed"};
String words[]={"started","movable","capable","competetion","rases","partition","moving","enocuraging"};
int i=0;
SuffixTrie st=new SuffixTrie();
TrieNode s1root=st.getSuffixRoot();

		System.out.println("suffixes");

for(i=0;i<5;i++)
{
			System.out.println("suffix \t"+suffixes[i]);

st.insertSuffix(suffixes[i]);
}

WordTrie wt=new WordTrie();
		System.out.println("words");

for(i=0;i<8;i++)
{
			System.out.println("word\t"+words[i]);

wt.insertWord(words[i],s1root);
}
System.out.println("Enter string to search");
Scanner sc=new Scanner(System.in);
String searchString=sc.nextLine();



Boolean res=wt.Search(searchString,s1root);

System.out.println(res);
}
}	