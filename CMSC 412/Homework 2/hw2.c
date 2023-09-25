#include <stdio.h>
#include <unistd.h>

int main() {

  int Child1, Child2, GrandChild1, GrandChild2;

  (Child1 = fork()) && (Child2 = fork());

  if (Child1 == 0) {

    GrandChild1 = fork();
    if (GrandChild1 == 0) {
      printf("I am the process GrandChild1 "
             "and my pid is: %i. My parent Child 1 has "
             "the following pid: %i.\n",
             getpid(), getppid());
    }
    wait(NULL);
    if (Child1 == 0 && GrandChild1 > 0) {
      printf("I am the process Child1 and my pid is: "
             "%i.\n", getpid());
    }

  } else if (Child2 == 0) {
    GrandChild2 = fork();

    if (GrandChild2 == 0) {
      printf("I am the process GrandChild2 "
             "and my pid is: %i. My parent Child2 has "
             "the following pid: %i. \n",
             getpid(), getppid());
    }
    wait(NULL);
    if (Child2 == 0 && GrandChild2 > 0) {
      printf("I am the process Child2 and my pid is: "
             "%i.\n", getpid());
    }
    else if (getpid() == -1){
      printf("an error has occurred/n");
    }

  } else {
    wait(NULL);
    wait(NULL);
    printf("I am the Parent process and my pid is:"
           "%i. Both my children finished their "
           "execution.", getpid());
  }
  return 0;
}