<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	<title>bienvenue!</title>
    <meta name="description" content="" />	    
	<meta name="keywords" content="Menu, reservation">
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
					<li data-slide="1" class="col-12 col-sm-2"><a id="menu-link-1" href="#slide-1" title="Next Section"><span class="icon icon-home"></span> <span class="text">ACCUEIL</span></a></li>
					<li data-slide="2" class="col-12 col-sm-2"><a id="menu-link-2" href="#slide-2" title="Next Section"><span class="icon icon-user"></span> <span class="text">A PROPOS DE NOUS</span></a></li>
					<li data-slide="3" class="col-12 col-sm-2"><a id="menu-link-3" href="#slide-3" title="Next Section"><span class="icon icon-briefcase"></span> <span class="text">MENU</span></a></li>
					<li data-slide="4" class="col-12 col-sm-2"><a id="menu-link-4" href="#slide-4" title="Next Section"><span class="icon icon-gears"></span> <span class="text">RESERVATION</span></a></li>
					<li data-slide="5" class="col-12 col-sm-2"><a id="menu-link-5" href="#slide-5" title="Next Section"><span class="icon icon-heart"></span> <span class="text">EMPLOYE</span></a></li>
					<li data-slide="6" class="col-12 col-sm-2"><a id="menu-link-6" href="#slide-6" title="Next Section"><span class="icon icon-envelope"></span> <span class="text">CONTACT</span></a></li>
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
	
	
	<!-- === MAIN Background === -->
	<div class="slide story" id="slide-1" data-slide="1">
		<div class="container">
			<div id="home-row-1" class="row clearfix">
				<div class="col-12">
					<h1 class="font-semibold">MEGAPHEH <span class="font-thin">RESTAURANT</span></h1>
					<h4 class="font-thin">nous sommes des <span class="font-semibold">points de restauration interactive</span> basée au Cameroun.</h4>
					<br>
					<br>
				</div><!-- /col-12 -->
			</div><!-- /row -->
			<div id="home-row-2" class="row clearfix">
				<div class="col-12 col-sm-4"><div class="home-hover navigation-slide" data-slide="5"><img src="images/s02.png"></div><span>PROFESSIONNELLE</span></div>
				<div class="col-12 col-sm-4"><div class="home-hover navigation-slide" data-slide="3"><img src="images/s01.png"></div><span>FLEXIBLE</span></div>
				<div class="col-12 col-sm-4"><div class="home-hover navigation-slide" data-slide="4"><img src="images/s03.png"></div><span>SUIVIE</span></div>
			</div><!-- /row -->
		</div><!-- /container -->
	</div><!-- /slide1 -->
	
	<!-- === Slide 2 === -->
	<div class="slide story" id="slide-2" data-slide="2">
		<div class="container">
			<div class="row title-row">
				<div class="col-12 font-thin">Et si vous vous imaginez entrain de <span class="font-semibold">manger</span> autrement.</div>
			</div><!-- /row -->
			<div class="row line-row">
				<div class="hr">&nbsp;</div>
			</div><!-- /row -->
			<div class="row subtitle-row">
				<div class="col-12 font-thin">This is what <span class="font-semibold">we do best</span></div>
			</div><!-- /row -->
			<div class="row content-row">
				<div class="col-12 col-lg-3 col-sm-6">
					<p><i class="icon icon-eye-open"></i></p>
					<h2 class="font-thin">Visual <span class="font-semibold">Identité</span></h2>
					<h4 class="font-thin">Venez découvrir une nouvelle façon de manager dans un cadre convivial et accueillant.</h4>
				</div><!-- /col12 -->
				<div class="col-12 col-lg-3 col-sm-6">
					<p><i class="icon icon-laptop"></i></p>
					<h2 class="font-thin">Mega Mpheh <span class="font-semibold">Web</span></h2>
					<h4 class="font-thin">commander et reserver à partir de votre ordinateur.</h4>
				</div><!-- /col12 -->
				<div class="col-12 col-lg-3 col-sm-6">
					<p><i class="icon icon-tablet"></i></p>
					<h2 class="font-thin">Mega Mpheh <span class="font-semibold">Mobile</span></h2>
					<h4 class="font-thin">commander et reserver à partir de votre telephone.</h4>
				</div><!-- /col12 -->
				<div class="col-12 col-lg-3 col-sm-6">
					<p><i class="icon icon-pencil"></i></p>
					<h2 class="font-thin">Mega Mpheh <span class="font-semibold">Local</span></h2>
					<h4 class="font-thin">commander et reserver à partir de n'importe lequel de nos points Mpheh.</h4>
				</div><!-- /col12 -->
			</div><!-- /row -->
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
				<div class="col-12 font-thin">Suivre une <span class="font-semibold">Reservation</span></div>
			</div><!-- /row -->
			<div class="row line-row">
				<div class="hr">&nbsp;</div>
			</div><!-- /row -->
			<div class="row subtitle-row">
				<div class="col-sm-1 hidden-sm">&nbsp;</div>
				<div class="col-12 col-sm-10 font-light">Vous satisfaire notre tâche au quotidien</div>			
				<div class="col-sm-1 hidden-sm">&nbsp;</div>
				<a href="#?w=700" rel="popup4" class="poplight btn btn-default">Suivre</a>
			</div><!-- /row -->
		</div><!-- /container -->
	</div><!-- /slide4 -->
	
	<!-- === Slide 5 === -->
	<div class="slide story" id="slide-5" data-slide="5">
		<div class="container">
			<div class="row title-row">
				<div class="col-12 font-thin"><span class="font-semibold">EMPLOYE</span> Megampheh</div>
			</div><!-- /row -->
			<div class="row line-row">
				<div class="hr">&nbsp;</div>
			</div><!-- /row -->
			
			<div class="row subtitle-row">
				
				<div class="col-sm-1 hidden-sm">&nbsp;</div>
				<div class="col-12 col-sm-10 font-light">
				<a href="#?w=700" rel="popup3" class="poplight btn btn-default">s'identifier</a>
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
			<div class="row title-row">
				<div class="col-12 font-light">Mega <span class="font-semibold">MPHEH</span></div>
			</div><!-- /row -->
			<div class="row line-row">
				<div class="hr">&nbsp;</div>
			</div><!-- /row -->
			<div class="row subtitle-row">
				<div class="col-sm-1 hidden-sm">&nbsp;</div>
				<div class="col-12 col-sm-10 font-light">Laisser nous un message</div>
				<div class="col-sm-1 hidden-sm">&nbsp;</div>
			</div><!-- /row -->
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
	Menu
	<nav class="navbar navbar-default" role="navigation">
	<div class="navbar-form navbar-left">
		Menu: <br/>
		Prix: <br/>
		<form class="navbar-form navbar-left" action="/MegaMPHEH/reservation" method="POST" role="search">
		<div class="form-group">
		<input type="hidden" name="code" value="1">
		</div>
			<button type="submit" class="btn btn-default">reserver</button>
		</form>
	</div>
	</nav>
    </div>
</div>
<div id="popup3" class="popup_block">
	<div>
	<span class="icon icon-user"></span>
	<div style="overflow:auto" >
                    <!-- hidden anchor to stop jump http://www.css3create.com/Astuce-Empecher-le-scroll-avec-l-utilisation-de-target#wrap4  -->
                    <a class="hiddenanchor" id="toregister"></a>
                    <a class="hiddenanchor" id="tologin"></a>
                    <div id="wrapper">
                        <div id="login" class="animate form">
                            <form  action="/MegaMPHEH/login" autocomplete="on" method="POST"> 
                                <h1>S'identifier</h1> 
                                <p> 
                                    <label for="username" class="uname" data-icon="u" > Votre email ou Login </label>
                                    <input id="username" name="useremail" required="required" type="text" placeholder="myusername or mymail@mail.com"/>
                                </p>
                                <p> 
                                    <label for="password" class="youpasswd" data-icon="p"> Votre mot de passe </label>
                                    <input id="password" name="userpwd" required="required" type="password" placeholder="eg. X8df!90EO" /> 
                                </p>
                                <p class="keeplogin"> 
									<input type="checkbox" name="loginkeeping" id="loginkeeping" value="loginkeeping" /> 
									<label for="loginkeeping">Keep me logged in</label>
								</p>
                                <p class="login button"> 
                                    <input type="submit" value="S'identifier" /> 
								</p>
                            </form>
                        </div>
						
                    </div>
                </div>
    </div>
</div>
<div id="popup4" class="popup_block">
	<div>
	
	<nav class="navbar navbar-default" role="navigation">
	<div class="navbar-header">
		<a class="navbar-brand" href="#">Suivez votre reservation</a>
	</div>
	<div>
		<form class="navbar-form navbar-left" action="/MegaMPHEH/reservation" method="POST" role="search">
		<div class="form-group">
		<input type="number" name="code" class="form-control" required="required" placeholder="code reservation">
		</div>
		<button type="submit" class="btn btn-default">envoyer</button>
		</form>
	</div>
	</nav>
    </div>
</div>
<div id="popup5" class="popup_block">
	<div>
	Contact
    </div>
</div>
<div id="popup6" class="popup_block">
	<div>
	Comptes
    </div>
</div>
<div id="popup7" class="popup_block">
	<div>
	Contacts
    </div>
</div>
<!--END POPUP-->

	
</body>
	<!-- SCRIPTS -->
	<script src="js/html5shiv.js"></script>
	<script src="js/jquery-1.10.2.min.js"></script>
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
			'hideOnContentClick':false,
			helpers: {
				overlay: {
					locked: false
				}
			},
			'type': 'iframe'
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