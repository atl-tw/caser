# define the name of the installer
Outfile "..\..\@artifactId@-@version@-installer.exe"

# define the directory to install to, the desktop in this case as specified
# by the predefined $DESKTOP variable
InstallDir $PROGRAMFILES\@artifactId@

# default section
Section

# define the output path for this file
SetOutPath $INSTDIR

# define what to install and place it in the output path
File lib\*
File bin\*
nsExec::Exec 'setx PATH=%PATH%;$INSTDIR\bin'
SectionEnd