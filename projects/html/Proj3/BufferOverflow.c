#include <stdio.h>
#include <string.h>

const int BUFSIZE = 1;
int main()
{
    //prinf() displays the string inside quotation
    printf("Hello, World!");
    char buf[BUFSIZE];
    strcpy(buf, "This will oveflow");
    printf("%s", buf);
    return 0;
}