### <div align="right"> 5 avril 2022</div>
### <div align="left">André Nora Marques, Ivan Vecerina, Dimitri De Bleser et Vincent Peer</div>
# <center> Projet DIL<center>

### Table des matières

* [Introdution](#intro)
* [Choix du modèle de processus logiciel](#ChoixModele)
* [Mode de collaboration](#ModeCollab)
* [Description de l'équipe](#DescrEquipe)
* [Sprint 1 - compte rendu](#CompteRendu)
    * [Objectifs du sprint](#Objectifs)
    * [Choix d'implémentation](#Choix)
    * [Méthodes de travail à changer](#toChange)

## Introduction <a class="anchor" id="intro"></a>
Le but de ce travail pratique et de prendre en main les outils et les méthodologies
de travail que nous serons amenés à utiliser lors de nos futurs projets dans le monde professionnel.
Le produit à développer représente un ensemble de commande du type build, init, clean, etc.
Nous allons réaliser plusieurs sprints pour contribuer étape par étape à la réalisation de
ce projet.
Nos clients sont notre professeur M. Bertil Chapuis et notre assistant M. Miguel Santamaria.
Lors de ce projet nous utilisons un projet partagé sur Github dans lequel nous
répartissons les tâches. Nous définissons un code de conduite et une license qui
visent à établir des règles que les membres sont priés de respecter.

## Choix du modèle de processus logiciel <a class="anchor" id="ChoixModele"></a>
Nous utilisons un modèle de processus de type agile.
Plusieurs ascpects nous dirigent vers ce modèle. Premièrement nous n'avons
jamais établi un tel projet auparavant, ce qui ne nous permet pas de prédire
les tâches exactes à réaliser ni le temps qu'elles prendraient pour être mises
en oeuvre, le modèle planifié ne nous correspond donc pas. Nous allons avancer de manière incrémentale, en s'adaptant
si besoin aux étapes à venir.
Ce modèle nous permet également d'évoluer tout en maintenant une collaboration avec les
clients, ce qui permet de rediriger le projet si nous venions
à nous éloigner par moment de l'objectif attendu.  
Nous utilisons un Kanban qui regroupe le travail à effectuer.
Il comporte plusieurs colonnes, la première représente la story venant des clients (product
backlog), regroupant leurs
souhaits pour le produit. On y trouve également notre propre story interne (processus backlog)
qui détaille les tâches que nous avons listées afin de réaliser la demande du client. D'autres colonnes informent de l'état actuel,
les tâches effectuées, celles en cours de dévelopement et celles non commencées.
Sa forme précise est la suivante:
<div style="text-align: center;">

|  Story Client | Internal story | To do | In progress | Done    | Potential addit. features | 
|---|----------------|:-----:|-------------|:----|---------------------------|

</div>
Nous mettons à jour manuellement ce Kanban. 
A la fin des sprints, nous rendons au client une release afin qu'il puisse voir l'état du projet. 
Cela permet également d'avoir un feedback du client sur le sprint terminé, ce qui offre la
possibilité d'établir rapidement des changements à apporter selon l'envie du client, ou si nous
avions mal compris certains aspects demandés.



## Mode de collaboration <a class="anchor" id="ModeCollab"></a>
Afin d'avoir une bonne collaboration dans l'équipe, nous avons établi un accord sur la
façon de travailler. Nous créons pour chaque tâche une issue Git, un membre annonce
qu'il se charge de la tâche et l'issue lui est assignée. Nous créons une nouvelle branche pour
chaque issue, afin que le membre qui s'en charge travaille sur cette branche. Une fois terminé,
il effectue une pull-request pour merger sa branche avec la branche principale. Suite à ça,
2 autres membres vont lire (review) le travail établi en examinant la pull-request, puis valider
si cela leur convient ou ne pas valider et annoncer les changements à effectuer pour cette
tâche. Une fois que 2 reviews ont été faites pour valider la pull-request, on peut merger
les branches et nous supprimons la branche propre à la tâche terminée.
Nos commits sont signés et rédigés en anglais.
L'ensemble des tâches à faire seront listées sous forme de story, nous sommes en
train de mettre ce système en place. Nous avons commencé par créer les tâches ensembles lors de la
création du kanban, ce qui a permis d'avoir une base pour commencer et voir comment débuter le projet.
Par la suite, Ivan a prit le rôle de scrum master et s'occupe de gérer les nouvelles tâches et d'en
assigner. Toutefois, les autres participant continues régulièrement à avoir des tâches qu'ils peuvent
également créer eux-mêmes et s'auto-assigner, cela arrive souvent pour les tâches qui se présentes
lorsqu'elles n'étaient pas prévisible facilement à l'avance.  
Nous avons un meeting hebdomadaire pendant lequel nous collaborons pendant environ 1h30. Entre ces
meetings, nous collaborons via un réseau de communication.




## Description de l'équipe <a class="anchor" id="DescrEquipe"></a>

Dès le début du projet, certains rôles s'installent selon les connaissances et l'expérience
de chacun.

**Ivan Vecerina** prend le rôle de scrum master. Doté de bonnes connaissances GitHub, il crée la
mojorité des issues dès le début du projet et assigne les tâches à effectuer. Il a également une
bonne vision du projet dans son ensemble ce qui permet d'anticiper certaines tâches. Il faut
cependant réussir à suivre ses demandes qui parfois sont claires pour lui mais un peu moins pour nous
autres qui travaillent sur le projet.  
**André Nora Marques** a également de bonnes connaissances pour l'utilisation de Git
ce qui le laisse prendre rapidement un bon rythme dans le projet, il execute en accord avec
Ivan des tâches qui demande certaines connaissances approfondies dans le domaine. Attention toutefois à
surveiller s'il est en train de se creuser la tête sur le projet ou s'il regarde les offre digitec du jour.  
**Dimitri De Bleser** est un bon tactical tornado, il ne peut malheureusement pas mettre en oeuvre cet
atout dans ce projet qui comporte très peu de code. Il a la bonne volonté de se lancer dans des tâches
inconnues pour lui, pour lesquelles il n'hésitera pas à contacter des professionnels afin de les résoudres.
Il faut avouer que nous avons de la peine à lui trouver des défauts, nous attendons d'abord qu'il se présente
à un meeting afin de le rencontrer et pouvoir détecter ses défauts.  
**Vincent Peer** a rapidement prit en main le suivi du projet et la rédaction du portofolio. Il rédige
des explications qui sont régulièrement entrecoupées par des tâches à effectuer, ce qui lui permet de
vivre le projet depuis l'intérieur comme depuis une facette plus extérieur. Voulant comprendre les choses
au détail près, il peut mettre une semaine pour la plus simple des tâches, puis finalement voir qu'il s'est en fait
tromper de branche GitHub et qu'il faut recommencer.

## Sprint 1 - compte rendu <a class="anchor" id="CompteRendu"></a>
### Objectifs du sprint <a class="anchor" id="Objectifs"></a>
Le but de ce sprint est d'implémenter les 4 sous-commandes de base pour générer le site statique.
Ce premier sprint a été pour nous une nouvelle approche pour réaliser un projet. Nous avons commencé par étudier
scrupulusement la demande du client afin d'en lister les différentes tâches qui se traduisent sous forme d'issues
sur GitHub. Cette phase nous a prit beaucoup de temps car nous n'étions pas vraiment au clair avec la demande et cette
nouvelle approche de projet. Il était difficile de visualiser la partie concrete du projet et la gestion du projet elle
même qui implique beaucoup de démarche autour du site statique. Ces difficultés nous ont retardés et nous n'avons pas
rempli l'entièreté de la demande pour ce premier sprint.


### Choix d'implémentation <a class="anchor" id="Choix"></a>
Pour la conversion de fichier markdown à html, nous voulions utilisé Aspose pour sa
simplicité mais la version gratuite n'est pas conforme à la demande du client car elle ajoute des "watermarks" après
la conversion. Nous avons essayé d'autres librairies (Flexmark, Mdtool, etc) et opté pour commonMark car c'est la
plus simple d'utilisation de notre point de vue en plus de sa specificité pour les fichiers markdown.  
Concernant les fichiers yaml, nous avons rajouté la librarie snakeYaml car il est conseillé de l'utiliser pour les
projets java touchant des fichiers yaml et c'est une librairie complète.

### Méthodes de travail à changer <a class="anchor" id="toChange"></a>
Concernant le suivi des tâches, Vincent s'est occupé du portofolio tandis qu'Ivan, le scrum master, s'occupait d'implémenter certaine commande.
Nous avons constaté qu'il serait plus simple qu'Ivan gère le portofolio lors des sprints car il a une vision plus
globale grâce à son poste de scrum master qui lui donne une vue d'ensemble sur le projet. Nous prévoyons donc ce changement
pour le deuxième sprint, Vincent s'occupera alors d'implémenter des commandes entre autres tâches.
De plus, une petite partie de nos commits n'étaient pas signés, nous allons donc faire en sorte qu'ils soient tous signés pour les
sprints à venir.






