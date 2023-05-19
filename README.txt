//Undo all staged files and directories
git reset


//Base syntax is 'git push -u origin <branch>' OR 'git push --set-upstream origin <branch>'
//Pushing the newly created branch to remote github

git push --set-upstream origin dept_emp_mic_service_ses_13
		//OR
git push -u dept_emp_mic_service_ses_13


//How to delete local and remote branches
Syntax

 //Remove Local Branch
git branch -d <branchname>

    //Remove Remote Branch
git push -d <remote_name> <branchname>

//Note: In most cases, <remote_name> will be origin.

//You can use -D or -d

//Delete Local Branch
//To delete the local branch, use one of the following:

git branch -d <branch_name>
git branch -D <branch_name>


Source
//https://stackoverflow.com/questions/2003505/how-do-i-delete-a-git-branch-locally-and-remotely


EXAMPLES

//In the following, Removing local Branch

F:\..>git branch -d dept_emp_mic_service_ses_13

Deleted branch dept_emp_mic_service_ses_13 (was de338a8).

//In the following, Removing Remote branch

F:\..>git push -d origin dept_emp_mic_service_ses_13

To https://github.com/ahzamfarhan/MicroServices_Ramesh.git
 - [deleted]         dept_emp_mic_service_ses_13


//How to checkout to different branch

//Syntax
git checkout <branch-name>

//Example
>git checkout microservices-webclient

//////////////

https://stackoverflow.com/questions/2765421/how-do-i-push-a-new-local-branch-to-a-remote-git-repository-and-track-it-too


///// HOW TO do Git with IntelliJ

https://www.jetbrains.com/help/idea/manage-branches.html#checkout-Git-branch