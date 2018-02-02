# include <stdio.h>

void config_calucalation(int *cells, int len);

void config_calucalation(int *cells, int len)
{
    int i;

    int new_cells[len+2];
    new_cells[0] = cells[len-1];
    for (i=0; i<len; i++)
    {
        new_cells[i+1] = cells[i];
    }
    new_cells[len+1] = cells[0];

    int result_cells[len];
    for (i=1; i<len+1; i++)
    {
        if (new_cells[i-1]==0 && new_cells[i+1]==0)
        {
            result_cells[i-1]=0;
        }
        else if (new_cells[i-1]==1 && new_cells[i+1]==1)
        {
            result_cells[i-1]=0;
        }
        else if (new_cells[i-1]==1 && new_cells[i+1]==0)
        {
            result_cells[i-1]=1;
        }
        else if (new_cells[i-1]==0 && new_cells[i+1]==1)
        {
            result_cells[i-1]=1;
        }
        else
        {
            printf("%s\n", "Cells configuration is wrong");
        }
    }

    for (i=0; i<len; i++)
    {
        cells[i] = result_cells[i];
    }

}



int main()
{
    // Input: 
    //cells[] -> initial configuration:
    int cells[] = {1, 0, 1, 1};
    // number of steps:
    int k = 2;
    
    int len =  sizeof(cells) / sizeof(cells[0]);
    int i;

    for (i=0; i<len; i++)
    {
        if ((cells[i]!=0) && (cells[i]!=1))
        {
            printf("%s\n", "Cells configuration is wrong");
            return 0;
        }   
    }

    //print initial configuration:
    printf("%s\n", "The initial configuration is: ");
    for (i=0; i<len; i++)
    {
        printf("%d ", cells[i]);
    }
    printf("\n");
    
    for (i=0; i<k; i++)
    {
        config_calucalation(cells, len);
    }

    //print final configuration:
    printf("The configuration after %d step will be: \n", k);
    for (i=0; i<len; i++)
    {
        printf("%d ", cells[i]);
    }
    printf("\n");

    return 0;
    
}

