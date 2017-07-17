#include <stdio.h>
#include <stdlib.h>
#define MAX_WORD_SIZE 50


// A node of ternary search tree
struct Node
{
    char data;
 
    // True if this character is last character of one of the words
    unsigned isEndOfString: 1;
 
    struct Node *left, *eq, *right;
};
 
// A utility function to create a new ternary search tree node
struct Node* newNode(char data)
{
    struct Node* temp = (struct Node*) malloc(sizeof( struct Node ));
    temp->data = data;
    temp->isEndOfString = 0;
    temp->left = temp->eq = temp->right = NULL;
    return temp;
}
 
// Function to insert a new word in a Ternary Search Tree
void insert(struct Node** root, char *word)
{
    // Base Case: Tree is empty
    if (!(*root))
        *root = newNode(*word);
 
    // If current character of word is smaller than root's character,
    // then insert this word in left subtree of root
    if ((*word) < (*root)->data)
        insert(&( (*root)->left ), word);
 
    // If current character of word is greate than root's character,
    // then insert this word in right subtree of root
    else if ((*word) > (*root)->data)
        insert(&( (*root)->right ), word);
 
    // If current character of word is same as root's character,
    else
    {
        if (*(word+1))
            insert(&( (*root)->eq ), word+1);
 
        // the last character of the word
        else{
            (*root)->data = 0;
            (*root)->isEndOfString = 1;
        }
						
        printf("%c\n", (*root)->data); 
      }

}
 

 
 

 

int searchTST(struct Node *root, char *word)
{
    printf("ebteree\n");
    if (!root)
        return 0;
    if (*word < (root)->data)
        return searchTST(root->left, word); 
    else if (*word > (root)->data)
        return searchTST(root->right, word);
    else
    {
        printf("%c\n", *word);
        if (*(word) == '\0')
            return root->isEndOfString;
        return searchTST(root->eq, word+1);
    }
}
 
struct Node* dictionary_read_from_file(const char * filename,struct Node *root)
{
    // attempts to open the file in read only mode
    FILE *file = fopen(filename, "r");

    if (!file)
    {
        printf("could not find/open file \"%s\"\n", filename);
        return 0;
    }

    char word[MAX_WORD_SIZE];
    int count = 0;

    // ensure that at least two items are being parsed (word & desc)
    while (fgets(word,MAX_WORD_SIZE,file) > 0)
    {

        insert(&root,word);
		}
        
    
        
    

    fclose(file);
    printf("parsed file \"%s\" with entries\n", filename);
    return root;
}

// Driver program to test above functions
int main(int argc,char **argv)
{
    struct Node *root = NULL;
		char word[MAX_WORD_SIZE];
    int ans;
		for (int i = 1; i < argc; i++)
    {
        root = dictionary_read_from_file(argv[i],root);
    }
    if(!root) printf("nulll\n");
        do
			{
				printf("Enter the word\n");
		 		scanf("%s",word);
        		searchTST(root,word)? printf("Found\n"): printf("Not Found\n");
        		printf("do you want to continue?(0/1)\n");
				scanf("%d",&ans);
			}while(ans==1);
    return 0;
}
