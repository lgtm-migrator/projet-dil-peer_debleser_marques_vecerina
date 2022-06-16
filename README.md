### <div align="right"> 5 avril 2022</div>
### <div align="left">André Nora Marques, Ivan Vecerina, Dimitri De Bleser et Vincent Peer</div>
# <center> Projet DIL<center>




### Installation and user manual
To generate a static site, we offer some commands that gradually prepare each
step to have a site that can be visited from a web browser. 

#### 1 : Initialize a new static site
```
statique init [path]  
```
Where _path_ specifies the directoy to initialize the content.  
This step will make a config.yaml and a index.md files, the first contains the configuration informations
and the other contains the welcome page.

#### 2 : Site building  
```
statique build [path]  
```
Where _path_ specifies the directoy to build the site.   
This command will make a new directory named _build_, it will generate the 
resulting .html files that will be use for the site.

#### 3 : Site cleaning
```
statique clean [-w, -watch] [path]  
```
Where _path_ specifies the directoy to be cleaned.  
This command will remove the directory _build_ previously created by the building.


#### 4 : Serve
```
statique serve [-w, -watch] [path]  
```
Where _path_ specifies static website's directory.
The build command will be called, previous build will be overwritten.
The website will be accessible on localhost TCP port 8080

#### 5 : Publish
```
statique publish [path]  
```
Where _path_ specifies static website's directory.
The build command will be called, previous build will be overwritten.
SFTP connexion information must be included int the configuration file _config.yaml_

#### 6 : version
```
statique --version  
```
Returns program's current version.
