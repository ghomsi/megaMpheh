<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Agenda</title>

	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
	<link rel="stylesheet" href="fancybox/jquery.fancybox-v=2.1.5.css" type="text/css" media="screen">
    <link rel="stylesheet" href="css/font-awesome.min.css" rel="stylesheet">
	
	<link rel="stylesheet" type="text/css" href="css/style.css">	

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
				}
			});
		}	

		renderCalendar();
	});

</script>
<style>

	body {
		margin-top: 40px;
		text-align: center;
		font-size: 14px;
		font-family: "Lucida Grande",Helvetica,Arial,Verdana,sans-serif;
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
<body>

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
					<li data-slide="1" class="col-12 col-sm-2"><a id="menu-link-1" href="#slide-1" title="Next Section"><span class="icon icon-home"></span> <span class="text">RESERVATION</span> </a></li>
					<li class="col-12 col-sm-2"><a onclick="window.location.href='/MegaMPHEH/deconnectionSuivie'" id="" href="#" title=""><span class="icon icon-log-out"></span> <span class="text">DECONNECTION</span></a></li>
				</ul>
			</div><!-- /.nav-collapse -->
		</div><!-- /.container -->
	</div><!-- /.navbar -->
	
	

	
	<div class="row">
				<div class="hr">&nbsp;</div>
				<div class="hr">&nbsp;</div>
				<div class="hr">&nbsp;</div>
				<div class="hr">&nbsp;</div>
	</div><!-- /row -->
	<div class="row title-row">
				<div class="col-12 font-light" style="color:white">Gérer votre <span class="font-semibold">Reservation</span></div>
	</div><!-- /row -->
	<div class="row">
				<div class="hr">&nbsp;</div>
	</div><!-- /row -->
	<div id='wrap'>
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
	</div>
	<!-- === Agenda === -->
		
	<!-- === MAIN Background === -->

	
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
			helpers: {
				overlay: {
					locked: false
				}
			}
		});
	
	});
	</script>
</html>