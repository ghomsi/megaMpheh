<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="fr"> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>point Mpheh</title>
<meta name="description" content="" />	    
	<meta name="keywords" content="">
	<meta property="og:title" content="">

	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
	<link rel="stylesheet" href="fancybox/jquery.fancybox-v=2.1.5.css" type="text/css" media="screen">
    <link rel="stylesheet" href="css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" href="css/animate-custom.css" rel="stylesheet">
    <link rel="stylesheet" href="css/demo.css" rel="stylesheet">
    <link rel="stylesheet" href="css/styleAd.css" rel="stylesheet">
	
	<link rel="stylesheet" type="text/css" href="css/style.css">	
	
	<link href='http://fonts.googleapis.com/css?family=Titillium+Web:400,600,300,200&subset=latin,latin-ext' rel='stylesheet' type='text/css'>
	
	
	<link rel="prefetch" href="images/zoom.png">
	
	<link href='css/fullcalendar.css' rel='stylesheet' />
<link href='css/fullcalendar.print.css' rel='stylesheet' media='print' />
<script src='js/lib/moment.min.js'></script>
<script src='js/lib/jquery.min.js'></script>
<script src='js/lib/jquery-ui.custom.min.js'></script>
<script src='js/fullcalendar.min.js'></script>
<script src='js/lang-all.js'></script>
	
	<script>

	$(document).ready(function() {

		var currentLangCode = 'fr';

		/* initialize the external events
		-----------------------------------------------------------------*/

		$('#external-events .fc-event').each(function() {

			// store data so the calendar knows to render an event upon drop
			$(this).data('event', {
				title: $.trim($(this).text()), // use the element's text as the event title
				stick: true // maintain when user navigates (see docs on the renderEvent method)
			});

			// make the event draggable using jQuery UI
			$(this).draggable({
				zIndex: 999,
				revert: true,      // will cause the event to go back to its
				revertDuration: 0  //  original position after the drag
			});

		});

		
		// rerender the calendar when the selected option changes
		$('#lang-selector').on('change', function() {
			if (this.value) {
				currentLangCode = this.value;
				$('#calendar').fullCalendar('destroy');
				renderCalendar();
			}
		});
		

		/* initialize the calendar
		-----------------------------------------------------------------*/
		function renderCalendar() {
			$('#calendar').fullCalendar({
				header: {
					left: 'prev,next today',
					center: 'title',
					right: 'month,agendaWeek,agendaDay'
				},
				defaultDate: '2015-12-12',
				lang: currentLangCode,
				buttonIcons: false, // show the prev/next text
				weekNumbers: true,
				editable: true,
				eventLimit: true, // allow "more" link when too many events
				editable: true,
				droppable: true, // this allows things to be dropped onto the calendar
				eventClick: function(calEvent, jsEvent, view) {

			        alert('Event: ' + calEvent.title);
			        alert('Coordinates: ' + jsEvent.pageX + ',' + jsEvent.pageY);
			        alert('View: ' + view.name);

			        // change the border color just for fun
			        $(this).css('border-color', 'red');

			    },
				drop: function() {
					// is the "remove after drop" checkbox checked?
					if ($('#drop-remove').is(':checked')) {
						// if so, remove the element from the "Draggable Events" list
						$(this).remove();
					}
				},
				events: [
							{
								title: 'Réunion de cordination',
								start: '2015-12-01'
							},
							{
								title: 'Seminaire',
								start: '2015-12-07',
								end: '2015-12-10'
							},
							{
								id: 999,
								title: 'Ndolè, 5place, Table1',
								start: '2015-12-09T16:00:00'
							},
							{
								id: 999,
								title: 'Pilé de pommes, 1place, Table2',
								start: '2015-12-16T16:00:00'
							},
							{
								title: 'Conference',
								start: '2015-12-11',
								end: '2015-12-13'
							},
							{
								title: 'Meeting',
								start: '2015-12-12T10:30:00',
								end: '2015-12-12T12:30:00'
							},
							{
								title: 'Pili Pili, 10 place, table 3 et table 4',
								start: '2015-12-12T12:00:00'
							},
							{
								title: 'Meeting',
								start: '2015-12-12T14:30:00'
							},
							{
								title: 'Happy Hour',
								start: '2015-12-12T17:30:00'
							},
							{
								title: 'Koki, 4 place, Table2',
								start: '2015-12-12T20:00:00'
							},
							{
								title: 'Anniverssaire, met de pistaches, 7 places, Table 9',
								start: '2015-12-13T07:00:00'
							},
							{
								title: 'ravitallement chez fournisseur',
								url: 'http://ww.complexdidi.cm/',
								start: '2015-12-28'
							}
						]

			});
		}	

		renderCalendar();
	});

</script>
<style>

	/*------------------POPUPS------------------------*/
#fade {
	display: none;
	background: #000; 
	position: fixed; left: 0; top: 0; 
	z-index: 10;
	width: 100%; height: 100%;
	opacity: .80;
	z-index: 9999;
}
.popup_block{
	display: none;
	background: #fff;
	padding: 20px; 	
	border: 20px solid #ddd;
	float: left;
	font-size: 1.2em;
	
	position: fixed;
	top: 50%; left: 50%;
	z-index: 99999;
	-webkit-box-shadow: 0px 0px 20px #000;
	-moz-box-shadow: 0px 0px 20px #000;
	box-shadow: 0px 0px 20px #000;
	-webkit-border-radius: 10px;
	-moz-border-radius: 10px;
	border-radius: 10px;
}
img.btn_close {
	float: right; 
	margin: -55px -55px 0 0;
}
.popup p {
	padding: 5px 10px;
	margin: 5px 0;
}
/*--Making IE6 Understand Fixed Positioning--*/
*html #fade {
	position: absolute;
}
*html .popup_block {
	position: absolute;
}



	#wrap {
		width: 1100px;
		margin: 0 auto;
	}
		
	#external-events {
		float: left;
		width: 150px;
		padding: 0 10px;
		border: 1px solid #ccc;
		background: #eee;
		text-align: left;
	}
		
	#external-events h4 {
		font-size: 16px;
		margin-top: 0;
		padding-top: 1em;
	}
		
	#external-events .fc-event {
		margin: 10px 0;
		cursor: pointer;
	}
		
	#external-events p {
		margin: 1.5em 0;
		font-size: 11px;
		color: #666;
	}
		
	#external-events p input {
		margin: 0;
		vertical-align: middle;
	}

	#calendar {
		float: right;
		width: 900px;
	}

</style>
</head>
<div class="navbar navbar-fixed-top" data-activeslide="1">
		<div class="container">
		
			<!-- .navbar-toggle is used as the toggle for collapsed navbar content -->
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-responsive-collapse">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			
			
			<div class="nav-collapse collapse navbar-responsive-collapse">
				<ul class="nav row">
					<li data-slide="1" class="col-12 col-sm-2"><a id="menu-link-1" href="#slide-1" title="Next Section"><span class="icon icon-home"></span> <span class="text">ACCUEIL</span></a></li>
					<li data-slide="3" class="col-12 col-sm-2"><a id="menu-link-3" href="#slide-3" title="Next Section"><span class="icon icon-leaf"></span> <span class="text">GERER MENUS</span></a></li>
					<li data-slide="4" class="col-12 col-sm-2"><a id="menu-link-4" href="#slide-4" title="Next Section"><span class="icon icon-table"></span> <span class="text">GERER TABLES</span></a></li>
					<li data-slide="5" class="col-12 col-sm-2"><a id="menu-link-5" href="#slide-5" title="Next Section"><span class="icon icon-group"></span> <span class="text">GERER CONTACTS</span></a></li>
					<c:choose>
						<c:when test="${sessionScope.utilisateur.type=='admin'}">
							<li data-slide="2" class="col-12 col-sm-2"><a id="menu-link-2" href="#slide-2" title="Next Section"><span class="icon icon-user"></span> <span class="text">GERER LES COMPTES </span></a></li>
						<li data-slide="6" class="col-12 col-sm-2"><a id="menu-link-6" href="#slide-6" title="Next Section"><span class="icon icon-calendar"></span> <span class="text">GERER LES POINTS MPHEH</span></a></li>
						</c:when>	
						<c:otherwise>
						<li data-slide="6" class="col-12 col-sm-2"><a id="menu-link-6" href="#slide-6" title="Next Section"><span class="icon icon-calendar"></span> <span class="text">MEGA MPHEH</span></a></li>
						</c:otherwise>
					</c:choose>
						
					<li data-slide="" class="col-12 col-sm-2"><a id="menu-link-7" onclick="window.location.href='/MegaMPHEH/logout'" href="#" title="Next Section"><span class="icon icon-log-in"></span> <span class="text">DECONNECTION</span></a></li>
				</ul>
				<div class="row">
					<div class="col-sm-2 active-menu"></div>
				</div>
			</div><!-- /.nav-collapse -->
		</div><!-- /.container -->
	</div><!-- /.navbar -->
	
	
	<!-- === Arrows === -->
	<div id="arrows">
		<div id="arrow-up" class="disabled"></div>
		<div id="arrow-down"></div>
		<div id="arrow-left" class="disabled visible-lg"></div>
		<div id="arrow-right" class="disabled visible-lg"></div>
	</div><!-- /.arrows -->
	<div class="row">
				<div class="hr">&nbsp;</div>
				<div class="hr">&nbsp;</div>
				<div class="hr">&nbsp;</div>
				<div class="hr">&nbsp;</div>
	</div><!-- /row -->
	<div class="row title-row">
				<div class="col-12 font-light" style="color:white">Gérer <span class="font-semibold">Agenda</span></div>
	</div><!-- /row -->
	<div class="row">
				<div class="hr">&nbsp;</div>
	</div><!-- /row -->
	<div id='wrap'>
			
						<div id='external-events'>
							<h4>Menu</h4>
							<div class='fc-event'>Ekomba</div>
							<div class='fc-event'>Folong au arrachide</div>
							<div class='fc-event'>KoKi</div>
							<div class='fc-event'>Ndolè</div>
							<div class='fc-event'>Mitumba</div>
							<div class='fc-event'>Poulet DG</div>
							<div class='fc-event'>Pilé de pommes</div>
							<div class='fc-event'>Pili Pili</div>
							<div class='fc-event'>Net de pistache</div>
							<div class='fc-event'>Corn tchap</div>
							<div class='fc-event'>Poisson grillé</div>
							<p>
								<input type='checkbox' id='drop-remove' />
								<label for='drop-remove'>retirer après la chute</label>
							</p>
						</div>
				
						<div id='calendar' style="background-color:white;"></div>
				
						<div style='clear:both'></div>
						
	</div>
	<div class="row">
				<div class="hr">&nbsp;</div>
				<div class="hr">&nbsp;</div>
				<div class="hr">&nbsp;</div>
				<div class="hr">&nbsp;</div>
	</div><!-- /row -->
	
	<!-- === MAIN Background === -->
	<div class="slide story" id="slide-1" data-slide="1">
		<div class="container">
			<div id="home-row-1" class="row clearfix">
				<div class="col-12">
					<h1 class="font-semibold"><c:out value="${sessionScope.travaila.nom}" /> <span class="font-thin">MEGAPHEH</span></h1>
					<h4 class="font-thin">Bienvenue <c:out value="${sessionScope.utilisateur.nom}" />&nbsp;<span class="font-semibold"><c:out value="${sessionScope.utilisateur.prenom}" /></span></h4>
					<br>
					<br>
				</div><!-- /col-12 -->
			</div><!-- /row -->
			<div id="home-row-2" class="row clearfix">
			<!-- 
				<div class="col-12 col-sm-4"><div class="home-hover navigation-slide" ><img src="images/s02.png"></div><span>PROFESSIONNELLE</span></div>
				<div class="col-12 col-sm-4"><div class="home-hover navigation-slide" ><img src="images/s01.png"></div><span>FLEXIBLE</span></div>
				<div class="col-12 col-sm-4"><div class="home-hover navigation-slide" ><img src="images/s03.png"></div><span>SUIVIE</span></div>
			 -->
			</div><!-- /row -->
		</div><!-- /container -->
	</div><!-- /slide1 -->
	
	
	
	<!-- === Slide 2 === -->
	<div class="slide story" id="slide-2" data-slide="2">
		<div class="container">
		<%-- Vérification de la présence d'un objet utilisateur en session --%>
		<c:if test="${sessionScope.utilisateur.type=='admin'}">
		<%-- Si l'utilisateur existe en session, alors on affiche son adresse email. --%>
			<div class="row title-row">
				<div class="col-12 font-thin">Gerer les <span class="font-semibold">Comptes Utilisateurs</span>.</div>
			</div><!-- /row -->
			<div class="row line-row">
				<div class="hr">&nbsp;</div>
			</div><!-- /row -->
			<div class="row subtitle-row">
				<div class="col-12 font-thin"><a href="#?w=700" rel="popup6" class="poplight">Ajouter un <span class="font-semibold">compte</span></a></div>
				
			</div><!-- /row -->
			<div class="row content-row">
				<c:choose>
				<%-- Si aucun client n'existe en session, affichage d'un message par défaut. --%>
				<c:when test="${ empty sessionScope.listeUtilisateur }">
				<div class="col-12 col-lg-3 col-sm-6">
					<p class="erreur">Aucun Utilisateur enregistré.</p>
				</div>	
				</c:when>
				<%-- Sinon, affichage du tableau. --%>
				<c:otherwise>
				<%-- Parcours de la Map des clients en session, et utilisation de l'objet varStatus. --%>
					<c:forEach items="${ sessionScope.listeUtilisateur }" var="mapUtilisateur" varStatus="boucle">
					<div class="col-12 col-lg-3 col-sm-6">
						<p><i class="icon icon-user"></i></p>
						<h2 class="font-thin"><c:out value="${ mapUtilisateur.value.nom}"/> <span class="font-semibold"><c:out value="${ mapUtilisateur.value.prenom}"/></span></h2>
						<h4 class="font-thin">née le <c:out value="${ mapUtilisateur.value.date}"/></h4>
						<h4 class="font-thin">Email <c:out value="${ mapUtilisateur.value.email}"/></h4>
						<h4 class="font-thin">Travail à <c:out value="${ mapUtilisateur.value.travaila}"/></h4>
						<h4 class="font-thin">type: <c:out value="${ mapUtilisateur.value.type}"/></h4>
						<h4 class="font-thin">Tel: <c:out value="${ mapUtilisateur.value.donnees.tel}"/></h4>
						<h4 class="font-thin">Pays: <c:out value="${ mapUtilisateur.value.donnees.pays}"/></h4>
						<h4 class="font-thin">Ville: <c:out value="${ mapUtilisateur.value.donnees.ville}"/></h4>
						<h4 class="font-thin">Adresse: <c:out value="${ mapUtilisateur.value.donnees.adresse}"/></h4>
						<h4 class="font-thin"><a href="#?w=700" rel="popup1" class="poplight">modifier</a></h4>
					</div><!-- /col12 -->
					</c:forEach>
				</c:otherwise>
				</c:choose>
		
				</div><!-- /col12 -->
			</div><!-- /row -->
			</c:if>
		</div><!-- /container -->
	</div><!-- /slide2 -->
 
	<!-- === SLide 3 - Portfolio -->
	<div class="slide story" id="slide-3" data-slide="3">
		<div class="row">
			<div class="col-12 col-sm-6 col-lg-2"><a data-fancybox-group="portfolio" href="#?w=700" rel="popup2" class="poplight"><img class="thumb" src="images/portfolio/p08-small.jpg" alt=""></a></div>
			<div class="col-12 col-sm-6 col-lg-2"><a data-fancybox-group="portfolio" class="fancybox" href="images/designrepas/p01.large.jpg"><img class="thumb" src="images/designrepas/p01.large.jpg" alt=""></a></div>
			<div class="col-12 col-sm-6 col-lg-2"><a data-fancybox-group="portfolio" class="fancybox" href="images/designrepas/p02.large.jpg"><img class="thumb" src="images/designrepas/p02.large.jpg" alt=""></a></div>
			<div class="col-12 col-sm-6 col-lg-2"><a data-fancybox-group="portfolio" class="fancybox" href="images/designrepas/p03.large.jpg"><img class="thumb" src="images/designrepas/p03.large.jpg" alt=""></a></div>
			<div class="col-12 col-sm-6 col-lg-2"><a data-fancybox-group="portfolio" class="fancybox" href="images/designrepas/p03.large.jpg"><img class="thumb" src="images/designrepas/p04.large.jpg" alt=""></a></div>
			<div class="col-12 col-sm-6 col-lg-2"><a data-fancybox-group="portfolio" class="fancybox" href="images/designrepas/p05.large.jpg"><img class="thumb" src="images/designrepas/p05.large.jpg" alt=""></a></div>
			<div class="col-12 col-sm-6 col-lg-2"><a data-fancybox-group="portfolio" class="fancybox" href="images/designrepas/p06.large.jpg"><img class="thumb" src="images/designrepas/p06.large.jpg" alt=""></a></div>
			<div class="col-12 col-sm-6 col-lg-2"><a data-fancybox-group="portfolio" class="fancybox" href="images/designrepas/p07.large.jpg"><img class="thumb" src="images/designrepas/p07.large.jpg" alt=""></a></div>
			<div class="col-12 col-sm-6 col-lg-2"><a data-fancybox-group="portfolio" class="fancybox" href="images/designrepas/p08.large.jpg"><img class="thumb" src="images/designrepas/p08.large.jpg" alt=""></a></div>
			<div class="col-12 col-sm-6 col-lg-2"><a data-fancybox-group="portfolio" class="fancybox" href="images/designrepas/p09.large.jpg"><img class="thumb" src="images/designrepas/p09.large.jpg" alt=""></a></div>
			<div class="col-12 col-sm-6 col-lg-2"><a data-fancybox-group="portfolio" class="fancybox" href="images/designrepas/p10.large.jpg"><img class="thumb" src="images/designrepas/p10.large.jpg" alt=""></a></div>
			<div class="col-12 col-sm-6 col-lg-2"><a data-fancybox-group="portfolio" class="fancybox" href="images/designrepas/p11.large.jpg"><img class="thumb" src="images/designrepas/p11.large.jpg" alt=""></a></div>
		</div><!-- /row -->
	</div><!-- /slide3 -->
	
	<!-- === Slide 4 - Process === -->
	<div class="slide story" id="slide-4" data-slide="4">
		<div class="container">
			<div class="row title-row">
				<div class="col-12 font-thin">Gerer les <span class="font-semibold">Tables</span></div>
			</div><!-- /row -->
			<div class="row line-row">
				<div class="hr">&nbsp;</div>
			</div><!-- /row -->
			<div class="row subtitle-row">
				<div class="col-sm-1 hidden-sm">&nbsp;</div>
				<div class="col-12 col-sm-10 font-light"><a href="#?w=700" rel="popup5" class="poplight">Ajouter une nouvelle table</a></div>
				<div class="col-sm-1 hidden-sm">&nbsp;</div>
			</div><!-- /row -->
			<div class="row content-row">
				<div class="col-sm-1 hidden-sm">&nbsp;</div>

				<c:choose>
					<%-- Si aucun client n'existe en session, affichage d'un message par défaut. --%>
					<c:when test="${ empty sessionScope.listeTable }">
					<p class="erreur">Aucune table enregistré.</p>
					</c:when>
					<%-- Sinon, affichage du tableau. --%>
					<c:otherwise>
					<%-- Parcours de la Map des clients en session, et utilisation de l'objet varStatus. --%>
					<c:forEach items="${ sessionScope.listeTable }" var="mapTable" varStatus="boucle">
					<div class="col-12 col-sm-2">
					
						<p><i class="icon icon-map-marker"></i></p>
						<h2 class="font-thin"><span class="font-semibold"><c:out value="${ mapTable.value.nom}"/></span></h2>
						<h4 class="font-thin">Capcité<span class="font-semibold"><c:out value="${ mapTable.value.nbreplace}"/></span></h4>
						<h4 class="font-thin">places Disponible<span class="font-semibold"><c:out value="${ mapTable.value.nbrepacedispo}"/></span></h4>
						<h4 class="font-thin"><a href="#?w=700" rel="popup3" class="poplight">modifier</a></h4>
					</div><!-- /col12 -->
					</c:forEach>
				</c:otherwise>
				</c:choose>
				<div class="col-sm-1 hidden-sm">&nbsp;</div>
			</div><!-- /row -->
		</div><!-- /container -->
	</div><!-- /slide4 -->
	
	<!-- === Slide 5 === -->
	<div class="slide story" id="slide-5" data-slide="5">
		<div class="container">
			<div class="row title-row">
				<div class="col-12 font-thin"><span class="font-semibold">Gerer les</span> Contacts</div>
			</div><!-- /row -->
			<div class="row line-row">
				<div class="hr">&nbsp;</div>
			</div><!-- /row -->
			<div class="row subtitle-row">
				<div class="col-12 font-thin"><a href="#?w=700" rel="popup7" class="poplight">Ajouter un <span class="font-semibold">contact</span></a></div>
				
			</div><!-- /row -->
			<div class="row">
				<div class="col-sm-1 hidden-sm">&nbsp;</div>
				<div class="col-12 col-sm-10 font-light">
				<c:choose>
					<%-- Si aucun client n'existe en session, affichage d'un message par défaut. --%>
					<c:when test="${ empty sessionScope.listeContact }">
					<p class="erreur">Aucun contact enregistré.</p>
					</c:when>
					<%-- Sinon, affichage du tableau. --%>
					<c:otherwise>
					<table class="table table-condensed">
						<thead>
						<tr class=" subtitle-row">
							<th>contacts</th>
							<th>Données</th>
							<th></th>
						</tr>
						</thead>
						<tbody>
						<tbody>
						<%-- Parcours de la Map des clients en session, et utilisation de l'objet varStatus. --%>
						<c:forEach items="${ sessionScope.listeContact }" var="mapContat" varStatus="boucle">
						<tr>
							<td>
							Nom: <c:out value="${ mapContact.value.nom}"/></br>
							Prenom: <c:out value="${ mapContact.value.prenom}"/></br>
							type: <c:out value="${ mapContact.value.type}"/>
							</td>
							<td>
							Tel: <c:out value="${ mapContact.value.donnees.tel}"/></br>
							Email: <c:out value="${ mapContact.value.donnees.email}"/></br>
							Pays: <c:out value="${ mapContact.value.donnees.pays}"/></br>
							Ville: <c:out value="${ mapContact.value.donnees.ville}"/></br>
							Adresse: <c:out value="${ mapContact.donnees.value.adresse}"/>
							</td>
							<td><a href="#?w=700" rel="popup4" class="poplight">modifier</a></td>
						</tr>
						</c:forEach>
						</tbody>
					</table>
					</c:otherwise>
					</c:choose>
				</div>
				<div class="col-sm-1 hidden-sm">&nbsp;</div>
			</div><!-- /row -->
			<!--<div class="row content-row">
				<div class="col-1 col-sm-1 hidden-sm">&nbsp;</div>
				<div class="col-12 col-sm-2"><img src="images/client01.png" alt=""></div>
				<div class="col-12 col-sm-2"><img src="images/client02.png" alt=""></div>
				<div class="col-12 col-sm-2"><img src="images/client03.png" alt=""></div>
				<div class="col-12 col-sm-2"><img src="images/client04.png" alt=""></div>
				<div class="col-12 col-sm-2"><img src="images/client05.png" alt=""></div>
				<div class="col-1 col-sm-1 hidden-sm">&nbsp;</div>-->
			</div><!-- /row -->
		</div><!-- /container -->
	</div><!-- /slide5 -->
	
	<!-- === Slide 6 / Contact === -->
	<div class="slide story" id="slide-6" data-slide="6">
		<div class="container">
			<c:choose>
				<c:when test="${sessionScope.utilisateur.type=='admin'}">
					<div class="row title-row">
						<div class="col-12 font-light">Gerer les points <span class="font-semibold">Mega MPHEH</span></div>
					</div><!-- /row -->
					<div class="row line-row">
						<div class="hr">&nbsp;</div>
					</div><!-- /row -->
					<div class="row subtitle-row">
						<div class="col-12 font-thin"><a href="#?w=700" rel="popup8" class="poplight">Ajouter un point <span class="font-semibold">Mpheh</span></a></div>
						
					</div><!-- /row -->
							<div class="row">
								<div class="col-sm-1 hidden-sm">&nbsp;</div>
								
								<div class="row">
								<div class="col-sm-1 hidden-sm">&nbsp;</div>
								<div class="col-12 col-sm-10 font-light">
								<c:choose>
									<%-- Si aucun client n'existe en session, affichage d'un message par défaut. --%>
									<c:when test="${ empty sessionScope.listePointDR }">
									<p class="erreur">Aucun point mpheh enregistré.</p>
									</c:when>
									<%-- Sinon, affichage du tableau. --%>
									<c:otherwise>
									<table class="table table-condensed">
										<thead>
										<tr class=" subtitle-row">
											<th>Point</th>
											<th>Données</th>
											<th></th>
										</tr>
										</thead>
										<tbody>
										<%-- Parcours de la Map des clients en session, et utilisation de l'objet varStatus. --%>
										<c:forEach items="${ sessionScope.listePointDR }" var="maplistePointDRs" varStatus="boucle">
										<tr>
											<td>
											Nom: <c:out value="${ mapListePointDRs.value.nom}"/></br>
											</td>
											<td>
											Tel: <c:out value="${ mapListePointDRs.value.donnees.tel}"/></br>
											Email: <c:out value="${ mapListePointDRs.value.donnees.email}"/></br>
											Pays: <c:out value="${ mapListePointDRs.value.donnees.pays}"/></br>
											Ville: <c:out value="${ mapListePointDRs.value.donnees.ville}"/></br>
											Adresse: <c:out value="${ mapListePointDRs.value.donnees.adresse}"/>
											</td>
											<td><a href="#?w=700" rel="popup9" class="poplight">modifier</a></td>
										</tr>
										</c:forEach>
										</tbody>
									</table>
									</c:otherwise>
									</c:choose>
								</div>
					<div class="col-sm-1 hidden-sm">&nbsp;</div>
					</div><!-- /row -->
				</c:when>
				<c:otherwise>
					<div class="row title-row">
					<div class="col-12 font-light">Direction <span class="font-semibold">Mega MPHEH</span></div>
				</div><!-- /row -->
				<div class="row line-row">
					<div class="hr">&nbsp;</div>
					<div class="row ">point de: <c:out value="${sessionScope.travaila.nom}" /> &nbsp; Telephone:<c:out value="${sessionScope.travaila.donnees.tel}" />&nbsp;Email:<c:out value="${sessionScope.travaila.donnees.email}" />&nbsp; adresse: <c:out value="${sessionScope.travaila.donnees.adresse}" /> </div>
				</div><!-- /row -->
				</c:otherwise>
			</c:choose>
			<div id="contact-row-4" class="row">
				<div class="col-sm-1 hidden-sm">&nbsp;</div>
				<div class="col-12 col-sm-2 with-hover-text">
					<p><a target="_blank" href="#"><i class="icon icon-phone"></i></a></p>
					<span class="hover-text font-light ">+237 677-431-223</span></a>
				</div><!-- /col12 -->
				<div class="col-12 col-sm-2 with-hover-text">
					<p><a target="_blank" href="#"><i class="icon icon-envelope"></i></a></p>
					<span class="hover-text font-light ">support@bmegampheh.cm</span></a>
				</div><!-- /col12 -->
				<div class="col-12 col-sm-2 with-hover-text">
					<p><a target="_blank" href="#"><i class="icon icon-home"></i></a></p>
					<span class="hover-text font-light ">Dschang, Cameroun<br>zip code 908</span></a>
				</div><!-- /col12 -->
				<div class="col-12 col-sm-2 with-hover-text">
					<p><a target="_blank" href="#"><i class="icon icon-facebook"></i></a></p>
					<span class="hover-text font-light ">facebook/megampheh_co</span></a>
				</div><!-- /col12 -->
				<div class="col-12 col-sm-2 with-hover-text">
					<p><a target="_blank" href="#"><i class="icon icon-twitter"></i></a></p>
					<span class="hover-text font-light ">@megampheh_co</span></a>
				</div><!-- /col12 -->
				<div class="col-sm-1 hidden-sm">&nbsp;</div>
			</div><!-- /row -->
			
		</div><!-- /container -->
	
	</div><!-- /Slide 6 -->
	
	
<!--POPUP START-->

<div id="popup1" class="popup_block">
	<div style="overflow:auto">
	<a class="hiddenanchor" id="toregister"></a>
    				<a class="hiddenanchor" id="tologin"></a>
                    <div id="wrapper">
                        <div id="login" class="animate form">
                            <h2 class="font-thin">Nom <span class="font-semibold">Prenom</span></h2>
							<h4 class="font-thin">née le</h4>
							<h4 class="font-thin">login</h4>
							<h4 class="font-thin">Travail à</h4>
							<h4 class="font-thin">nivo acces</h4>
							<form  action="mysuperscript.php" autocomplete="on"> 
                                <p>
									Not a member yet ?
									<a onclick="Javascript:window.document.location.href='#toregister';" class="to_register">Join us</a>
								</p>
                            </form>
                        </div>

                        <div id="register" class="animate form">
                            <form  action="mysuperscript.php" autocomplete="on">
                            	<h2 class="font-thin">
	                            	<label for="usernamesignup" class="uname" data-icon="u">Nom</label>
                                    <input id="usernamesignup" name="usernamesignup" required="required" type="text" placeholder="mysuperusername690" />
								</h2>
								<h2 class="font-thin">
	                            	<label for="usernamesignup" class="uname" data-icon="u">Prenom</label>
                                    <input id="usernamesignup" name="usernamesignup" required="required" type="text" placeholder="mysuperusername690" />
								</h2>
								<h4 class="font-thin">
									<label for="usernamesignup" class="uname" data-icon="u">Date de naissance</label>
                                    <input id="usernamesignup" name="usernamesignup" required="required" type="text" placeholder="mysuperusername690" />
								</h4>
								<h4 class="font-thin">
									<label for="usernamesignup" class="uname" data-icon="u">Lieu de travail</label>
                                    <input id="usernamesignup" name="usernamesignup" required="required" type="text" placeholder="mysuperusername690" />
								</h4>
								<h4 class="font-thin">
									<label for="usernamesignup" class="uname" data-icon="u">Nivo d'accès</label>
                                    <input id="usernamesignup" name="usernamesignup" required="required" type="text" placeholder="mysuperusername690" />
								</h4>
								
                                
                                <p class="signin button"> 
									<input type="submit" value="modifier"/> 
								</p>
                                <p>  
									
									<a onclick="Javascript:window.document.location.href='#tologin';" class="to_register"> Afficher </a>
								</p>
                            </form>
                        </div>
						
                    </div>
    </div>
</div>
<div id="popup2" class="popup_block">
	<div>
	<nav class="navbar navbar-default" role="navigation">
	<div class="navbar-header">
		<a class="navbar-brand" href="#">Modifier Menu</a>
	</div>
	<div>
		<form class="navbar-form navbar-left" action="/MegaMPHEH/modifiermenu" method="POST" role="search">
		<div class="form-group">
		<input type="text" name="nomMenu" class="form-control" placeholder="Nom Menu">
		</div>
		<div class="form-group">
		<input type="text" name="prix" class="form-control" placeholder="prix">
		</div>
		<div class="form-group">
		<input type="file" name="image" class="form-control" placeholder="image">
		</div>
		<button type="submit" class="btn btn-default">modifier</button>
		</form>
	</div>
	</nav>
	</div>
	</nav>
    </div>
</div>
<div id="popup3" class="popup_block">
	<div>
	<nav class="navbar navbar-default" role="navigation">
	<div class="navbar-header">
		<a class="navbar-brand" href="#">Modifier table</a>
	</div>
	<div>
		<form class="navbar-form navbar-left" action="/MegaMPHEH/ajoutertable" method="POST" role="search">
		<div class="form-group">
		<input type="text" name="nomtable" class="form-control" placeholder="Nom table">
		</div>
		<div class="form-group">
		<input type="text" name="nomtable" class="form-control" placeholder="nombre de place">
		</div>
		<button type="submit" class="btn btn-default">modifier</button>
		</form>
	</div>
	</nav>
    </div>
</div>
<div id="popup4" class="popup_block">
	<div>
	Ajouter une table
	<nav class="navbar navbar-default" role="navigation">
	<div class="navbar-header">
		<a class="navbar-brand" href="#">Ajouter une table</a>
	</div>
	<div>
		<form class="navbar-form navbar-left" action="/MegaMPHEH/ajoutertable" method="POST" role="search">
		<div class="form-group">
		<input type="text" name="nomtable" class="form-control" placeholder="Nom table">
		</div>
		<div class="form-group">
		<input type="text" name="nomtable" class="form-control" placeholder="nombre de place">
		</div>
		<button type="submit" class="btn btn-default">ajouter</button>
		</form>
	</div>
	</nav>
    </div>
</div>
<div id="popup5" class="popup_block">
	<div>
	<nav class="navbar navbar-default" role="navigation">
	<div class="navbar-header">
		<a class="navbar-brand" href="#">Ajouter une table</a>
	</div>
	<div>
		<form class="navbar-form navbar-left" action="/MegaMPHEH/ajoutertable" method="POST" role="search">
		<div class="form-group">
		<input type="text" name="tablenom" class="form-control" placeholder="Nom table">
		</div>
		<div class="form-group">
		<input type="text" name="tablenbreplace" class="form-control" placeholder="nombre de place">
		</div>
		<button type="submit" class="btn btn-default">ajouter</button>
		</form>
	</div>
	</nav>
    </div>
</div>
<div id="popup6" class="popup_block" >
	<div style="overflow:auto">
		<nav class="navbar navbar-default" role="navigation">
		<div class="navbar-header">
			<a class="navbar-brand" href="#">Ajouter un compte</a>
		</div>
		<div >
	                            <form  class="navbar-form navbar-left" action="/MegaMPHEH/ajouterUtilisateur" method="POST"  autocomplete="on">
	                            	<div class="form-group">
										<input class="form-control" id="nom" name="usernom" required="required" type="text" placeholder="Nom" />
									</div>
									<div class="form-group">
										<input class="form-control" id="prenom" name="userprenom" required="required" type="text" placeholder="Prenom" />
									</div>
									<div class="form-group">
										<input class="form-control" id="pwd" name="userpwd" required="required" type="text" placeholder="mot de passe" />
									</div>
									<div class="form-group">
										<label for="nivoacces">Type</label><br />
										<select name="usertype" id="nivoacces">
											<option value="admin">administrateur</option>
											<option value="employe">employé</option>									</select>
									</div>
									<div class="form-group">
										<label for="nivoacces">Affecter à</label><br />
										<select name="usertravaila" id="travailA">
											<option value="1">point mpheh foto</option>
											<option value="2">poiny mpheh foréké</option>									</select>
									</div>
									<div class="form-group">
										<label for="date">date de recrutement</label><br />
										<input class="form-control" id="date" name="userdate" required="required" type="date" />
									</div>
									<div class="form-group">
										<input class="form-control" id="tel" name="usertel" required="required" type="tel" placeholder="Numero:670000001" />
									</div>
									<div class="form-group">
										<input class="form-control" id="email" name="useremail" required="required" type="email" placeholder="Email" />
									</div>
									<div class="form-group">
										<input class="form-control" id="pays" name="userpays" required="required" type="text" placeholder="Pays" />
									</div>
									<div class="form-group">
										<input class="form-control" id="ville" name="userville" required="required" type="text" placeholder="Ville" />
									</div>
									<div class="form-group">
										<input class="form-control" id="adresse" name="useradresse" required="required" type="text" placeholder="Adresse" />
									</div>
									<button type="submit" class="btn btn-default">ajouter</button>
	                            </form>
           </div>
        </nav>
    </div>
</div>
<div id="popup7" class="popup_block">
	<div style="overflow:auto">
		<nav class="navbar navbar-default" role="navigation">
		<div class="navbar-header">
			<a class="navbar-brand" href="#">Ajouter un contact</a>
		</div>
		<div >
	                            <form  class="navbar-form navbar-left" action="/MegaMPHEH/ajoutercontact" method="POST"  autocomplete="on">
	                            	<div class="form-group">
										<input class="form-control" id="nom" name="contactnom" required="required" type="text" placeholder="Nom" />
									</div>
									<div class="form-group">
										<input class="form-control" id="prenom" name="contactprenom" required="required" type="text" placeholder="Prenom" />
									</div>
									<div class="form-group">
										<input class="form-control" id="tel" name="contacttel" required="required" type="tel" placeholder="Numero:670000001" />
									</div>
									<div class="form-group">
										<input class="form-control" id="email" name="contactemail" required="required" type="email" placeholder="Email" />
									</div>
									<div class="form-group">
										<input class="form-control" id="pays" name="contactpays" required="required" type="text" placeholder="Pays" />
									</div>
									<div class="form-group">
										<input class="form-control" id="ville" name="contactville" required="required" type="text" placeholder="Ville" />
									</div>
									<div class="form-group">
										<input class="form-control" id="adresse" name="contactadresse" required="required" type="text" placeholder="Adresse" />
									</div>
									<button type="submit" class="btn btn-default">ajouter</button>
	                            </form>
           </div>
        </nav>
    </div>
</div>
<div id="popup8" class="popup_block">
	<div style="overflow:auto">
		<nav class="navbar navbar-default" role="navigation">
		<div class="navbar-header">
			<a class="navbar-brand" href="#">Ajouter un point Mega mpheh</a>
		</div>
		<div >
	                            <form  class="navbar-form navbar-left" action="/MegaMPHEH/ajoutermpheh" method="POST"  autocomplete="on">
	                            	<div class="form-group">
										<input class="form-control" id="nom" name="mphehnom" required="required" type="text" placeholder="Nom" />
									</div>
									<div class="form-group">
										<input class="form-control" id="tel" name="mphehtel" required="required" type="tel" placeholder="Numero:670000001" />
									</div>
									<div class="form-group">
										<input class="form-control" id="email" name="mphehemail" required="required" type="email" placeholder="Email" />
									</div>
									<div class="form-group">
										<input class="form-control" id="pays" name="mphehpays" required="required" type="text" placeholder="Pays" />
									</div>
									<div class="form-group">
										<input class="form-control" id="ville" name="mphehville" required="required" type="text" placeholder="Ville" />
									</div>
									<div class="form-group">
										<input class="form-control" id="adresse" name="mphehadresse" required="required" type="text" placeholder="Adresse" />
									</div>
									<button type="submit" class="btn btn-default">ajouter</button>
	                            </form>
           </div>
        </nav>
    </div>
</div>
<div id="popup9" class="popup_block">
	<div style="overflow:auto">
		<nav class="navbar navbar-default" role="navigation">
		<div class="navbar-header">
			<a class="navbar-brand" href="#">modifier un point Mega mpheh</a>
		</div>
		<div >
	                            <form  class="navbar-form navbar-left" action="/MegaMPHEH/modifiermpheh" method="POST"  autocomplete="on">
	                            	<div class="form-group">
										<input class="form-control" id="nom" name="mphehnom" required="required" type="text" placeholder="Nom" />
									</div>
									<div class="form-group">
										<input class="form-control" id="tel" name="mphehtel" required="required" type="tel" placeholder="Numero:670000001" />
									</div>
									<div class="form-group">
										<input class="form-control" id="email" name="mphehemail" required="required" type="email" placeholder="Email" />
									</div>
									<div class="form-group">
										<input class="form-control" id="pays" name="mphehpays" required="required" type="text" placeholder="Pays" />
									</div>
									<div class="form-group">
										<input class="form-control" id="ville" name="mphehville" required="required" type="text" placeholder="Ville" />
									</div>
									<div class="form-group">
										<input class="form-control" id="adresse" name="mphehadresse" required="required" type="text" placeholder="Adresse" />
									</div>
									<button type="submit" class="btn btn-default">ajouter</button>
	                            </form>
           </div>
        </nav>
    </div>
</div>

<div id="popup10" class="popup_block">
	<div style="overflow:auto">
		<nav class="navbar navbar-default" role="navigation">
		<div class="navbar-header">
			<a class="navbar-brand" href="#">Ajouter menu</a>
		</div>
		<div >
	                            <form  class="navbar-form navbar-left" action="/MegaMPHEH/ajoutercontact" method="POST"  autocomplete="on">
	                            	<div class="form-group">
										<input class="form-control" id="nom" name="contactnom" required="required" type="text" placeholder="Nom" />
									</div>
									<div class="form-group">
										<input class="form-control" id="prenom" name="contactprenom" required="required" type="text" placeholder="Prenom" />
									</div>
									<div class="form-group">
										<input class="form-control" id="tel" name="contacttel" required="required" type="tel" placeholder="Numero:670000001" />
									</div>
									<div class="form-group">
										<input class="form-control" id="email" name="contactemail" required="required" type="email" placeholder="Email" />
									</div>
									<div class="form-group">
										<input class="form-control" id="pays" name="contactpays" required="required" type="text" placeholder="Pays" />
									</div>
									<div class="form-group">
										<input class="form-control" id="ville" name="contactville" required="required" type="text" placeholder="Ville" />
									</div>
									<div class="form-group">
										<input class="form-control" id="adresse" name="contactadresse" required="required" type="text" placeholder="Adresse" />
									</div>
									<button type="submit" class="btn btn-default">ajouter</button>
	                            </form>
           </div>
        </nav>
    </div>
</div>
<!--END POPUP-->
	
</body>

	<!-- SCRIPTS -->
	<script src="js/html5shiv.js"></script>
	<script src="js/jquery-migrate-1.2.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/jquery.easing.1.3.js"></script>
	<script type="text/javascript" src="fancybox/jquery.fancybox.pack-v=2.1.5.js"></script>
	<script src="js/script.js"></script>
	
	<!-- fancybox init -->
	<script>
	$(document).ready(function(e) {
		var lis = $('.nav > li');
		menu_focus( lis[0], 1 );
		
		$(".fancybox").fancybox({
			padding: 10,
			'type': 'iframe',
			'href': this.href,
			
			helpers: {
				overlay: {
					locked: false
				}
			}
		});
		
		//When you click on a link with class of poplight and the href starts with a # 
		$('a.poplight[href^=#]').click(function() {
			var popID = $(this).attr('rel'); //Get Popup Name
			var popURL = $(this).attr('href'); //Get Popup href to define size
					
			//Pull Query & Variables from href URL
			var query= popURL.split('?');
			var dim= query[1].split('&');
			var popWidth = dim[0].split('=')[1]; //Gets the first query string value

			//Fade in the Popup and add close button
			$('#' + popID).fadeIn().css({ 'width': Number( popWidth ) }).prepend('<a href="#" class="close"><img src="images/close_pop.png" class="btn_close" title="Close Window" alt="Close" /></a>');
			
			//Define margin for center alignment (vertical + horizontal) - we add 80 to the height/width to accomodate for the padding + border width defined in the css
			var popMargTop = ($('#' + popID).height() + 80) / 2;
			var popMargLeft = ($('#' + popID).width() + 80) / 2;
			
			//Apply Margin to Popup
			$('#' + popID).css({ 
				'margin-top' : -popMargTop,
				'margin-left' : -popMargLeft
			});
			
			//Fade in Background
			$('body').append('<div id="fade"></div>'); //Add the fade layer to bottom of the body tag.
			$('#fade').css({'filter' : 'alpha(opacity=80)'}).fadeIn(); //Fade in the fade layer 
			
			return false;
		});
		
		
		//Close Popups and Fade Layer
		$('a.close, #fade').live('click', function() { //When clicking on the close or fade layer...
		  	$('#fade , .popup_block').fadeOut(function() {
				$('#fade, a.close').remove();  
		}); //fade them both out
			
			return false;
		});
		
	
	});
	</script>
</html>