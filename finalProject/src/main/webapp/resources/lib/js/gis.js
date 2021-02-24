

var gis = function(){
	var ajaxUtils = ajaxUtil();
	var map,view;
	
	
	var GEOSERVER_URL = "http://192.168.58.190:8888/geoserver";
	var BASE_MAP_URL = "http://xdworld.vworld.kr:8080/2d/Base/201710/{z}/{x}/{y}.png";
	var BASE_MAPS_NM = ['base'];
	
	var layers = {}, sources = {}, selectedFeature = [], hoveredFeature = [];
	var layerName;
	var sourceName;
	var hsourceName;
	
	var image = new ol.style.Style({image: new ol.style.Icon({anchor: [30, 30], size: [60,60], scale: 0.5, anchorXUnits: 'pixels', anchorYUnits: 'pixels', src: '/resources/lib/images/reBoxMarker.png'})});
	
	var safeInfoKeys = ['GRADE_VAL', 'GRADE_A', 'GRADE_B', 'GRADE_C', 'GRADE_D', 'GRADE_E'];
	var safeInfoTitles = ['평균안전등급', 'A 등급', 'B 등급', 'C 등급', 'D 등급', 'E 등급'];
	var SAFE_WMS_NAME = [];
    var HIGHLIGHT_WMS_INFO = [
        {name : 'KOREA_SGG_HIGHLIGHT',  visible : true},
        {name : 'KOREA_SD_HIGHLIGHT',   visible : true}
    ];
	
    layers = {
    		
    }
    
    
    /*HIGHLIGHT_WMS_INFO.forEach(function (infos) {
        if(!(infos.name in sources)) {
            sources[infos.name] = new ol.source.ImageWMS({
                url: GEOSERVER_URL + '/geo/wms',
                params: {LAYERS: 'geo:' + infos.name, 'VERSION': '1.1.0', 'SRS': 'EPSG:4326'},
                serverType: 'geoserver'
            });
            layers[infos.name] = new ol.layer.Image({
                name: infos.name,
                source: sources[infos.name],
                minResolution: 0,
                maxResolution: 100000000,
                visible: infos.visible
            });
            map.addLayer(layers[infos.name]);
        }
    });*/
    
    
    
		//DFF1A77D-5B41-3B8D-8397-33C019C13278
    
    var hsource = new ol.source.ImageWMS({
        url: GEOSERVER_URL + '/geo/wms',
        params: {LAYERS: 'geo:' + 'KOREA_SD_HIGHLIGHT', 'VERSION': '1.1.0', 'SRS': 'EPSG:4326'},
        serverType: 'geoserver'
    });
    var hsource2 = new ol.source.ImageWMS({
        url: GEOSERVER_URL + '/geo/wms',
        params: {LAYERS: 'geo:' + 'KOREA_SGG_HIGHLIGHT', 'VERSION': '1.1.0', 'SRS': 'EPSG:4326'},
        serverType: 'geoserver'
    });
    var source = new ol.source.ImageWMS({
        url: GEOSERVER_URL + '/geo/wms',
        params: {LAYERS: 'geo:' + 'SELL_SD', 'VERSION': '1.1.0', 'SRS': 'EPSG:4326'},
        serverType: 'geoserver'
    });

    
    var SELL_SD = new ol.layer.Image({
        name: 'SELL_SD',
        source: source,
        minResolution: 0,
        maxResolution: 100000000,
        //visible: true
    });
    var SELL_SD2 = new ol.layer.Image({
        name: 'KOREA_SD_HIGHLIGHT',
        source: hsource,
        minResolution: 0,
        maxResolution: 100000000,
        //visible: true
    });
    
    var source2 = new ol.source.ImageWMS({
        url: GEOSERVER_URL + '/geo/wms',
        params: {LAYERS: 'geo:' + 'SELL_SGG', 'VERSION': '1.1.0', 'SRS': 'EPSG:4326'},
        serverType: 'geoserver'
    });

    
    var SELL_SGG = new ol.layer.Image({
        name: 'SELL_SGG',
        source: source2,
        minResolution: 0,
        maxResolution: 100000000,
        visible: false
    });
    
    var SELL_SGG2 = new ol.layer.Image({
        name: 'KOREA_SGG_HIGHLIGHT',
        source: hsource2,
        minResolution: 0,
        maxResolution: 100000000,
        visible: false
    });
    
    
    
    
    
    layers = {
    		'SELL_SD' : SELL_SD,
    		'SELL_SGG' : SELL_SGG,
    		'H_SELL_SD' : SELL_SD2,
    		'H_SELL_SGG' : SELL_SGG2,
    		'SELL_PLACE' : 'SELL_PLACE'
    		
    		
    }
    sources = {
    		'SELL_SD' : source,
    		'SELL_SGG' : source2,
    		'H_SELL_SD' : hsource,
    		'H_SELL_SGG' : hsource2,
    		'SELL_PLACE' : 'SELL_PLACE'
    }
    
    sources['SELL_PLACE'] = new ol.source.Vector();
    
    layers['SELL_PLACE'] = new ol.layer.Vector({
        source : sources['SELL_PLACE'],
        style : image
    });
    layers['SELL_PLACE'].setVisible(false);
    
    layerName = 'SELL_SD';
	sourceName = 'SELL_SD';
	hsourceName = 'H_SELL_SD';
    
    
    
    
	
	
	
	
    
    
    function mapChange(){  	
    	var beforeLayerName;
    	var beforeSourceName;
    	var beforeHsourceName;
    	

		$('#categoryList2').html('');
		$('#categoryList3').html('');
		

		 
		var th1 = $("<option value='all'>중분류</option>");
		th1.appendTo('#categoryList2');  
		 
		var th1 = $("<option value='all'>소분류</option>");
		th1.appendTo('#categoryList3');
    	
		$('#categoryList1').val('all');
    	$('#categoryList2').val('all');
		$('#categoryList3').val('all');
    	
    	
    	$('#mtd').html('');
    	$('#mth2').html('');
    	$('#mtd2').html('');
    	$('#PageWrap').html('');
    	layerName = $('#mapList option:selected').val();
    	sourceName = $('#mapList option:selected').val();
    	hsourceName = 'H_' + $('#mapList option:selected').val();
    	
    	
    	console.log(layers['SELL_SD'].getVisible());
    	console.log(layers['SELL_SGG'].getVisible());
    	console.log(layers['SELL_PLACE'].getVisible());
    	if(layers['SELL_SD'].getVisible()){
    		console.log("1");
    		beforeLayerName = 'SELL_SD';
        	beforeSourceName = 'SELL_SD';
        	beforeHsourceName = 'H_SELL_SD';
        }
        else if(layers['SELL_SGG'].getVisible()){
        	console.log("2");
        	beforeLayerName = 'SELL_SGG';
        	beforeSourceName = 'SELL_SGG';
        	beforeHsourceName = 'H_SELL_SGG';
        }else if(layers['SELL_PLACE'].getVisible()){
        	console.log("3");
        	beforeLayerName = 'SELL_PLACE';
        	beforeSourceName = 'SELL_PLACE';
        	beforeHsourceName = 'SELL_PLACE';
        }
    	console.log("4");
    	map.removeLayer(layers[beforeLayerName]);
		map.removeLayer(layers[beforeHsourceName]);
		
		layers[beforeLayerName].setVisible(false);
		layers[beforeHsourceName].setVisible(false);
    	if(layerName == 'SELL_SD'){
    		
    		map.addLayer(layers[layerName]);
    		map.addLayer(layers[hsourceName]);

    		layers[layerName].setVisible(true);
    		layers[hsourceName].setVisible(true);
    		map.getView().setZoom(8);
    	}else if(layerName == 'SELL_SGG'){
    		
    		map.addLayer(layers[layerName]);
    		map.addLayer(layers[hsourceName]);
    		
    		layers[layerName].setVisible(true);
    		layers[hsourceName].setVisible(true);
    		map.getView().setZoom(8);
    	}else if('MARKER'){
    		
    		var image = new ol.style.Style({image: new ol.style.Icon({anchor: [30, 30], size: [60,60], scale: 0.5, anchorXUnits: 'pixels', anchorYUnits: 'pixels', src: '/resources/lib/images/reBoxMarker.png'})});
    		var param = {}
    		//ajaxUtils.doPost('/storeList.do', param, storeMap);
    		
            
    		
    		
    		
    		var featureRequest = new ol.format.WFS().writeGetFeature({
                srsName: 'EPSG:4326',
                featurePrefix: 'geo',
                featureTypes: ['SELL_PLACE'],
                outputFormat: 'application/json',
                //filter: getFilterConditions(filterParam)
            });
            fetch(GEOSERVER_URL+'/wfs', {
                method: 'POST',
                body: new XMLSerializer().serializeToString(featureRequest)
            }).then(function (response) {
                return response.json();
            }).then(function (json) {
                console.log(json);
                
                
                
                
                
                var features = new ol.format.GeoJSON().readFeatures(json,{featureProjection:"EPSG:3857"});
                console.log(features);
                //makeTest(features);
                //var imageA = new ol.style.Style({image: new ol.style.Icon({anchor: [30, 30], size: [60,60], scale: 0.5, anchorXUnits: 'pixels', anchorYUnits: 'pixels', src: '/resources/lib/images/bizPoint.png'})});
                //features.setStyle(imageA);
                
                
                sources['SELL_PLACE'].clear();
                sources['SELL_PLACE'].addFeatures(features);
                
                /*layers['SELL_PLACE'] = new ol.layer.Vector({
            		source : sources['SELL_PLACE'],
            		visible : true
            	});*/
                
                layers['SELL_PLACE'] = new ol.layer.Vector({
                    source : sources['SELL_PLACE'],
                    style : image
                });
                
                map.addLayer(layers['SELL_PLACE']);
        		
        		
                layers['SELL_PLACE'].setVisible(true);
        		
                
                
                if(features.length > 1){
                	
                	map.getView().setZoom(10);
                    map.getView().fit(sources['SELL_PLACE'].getExtent());
                    
            	}
            
            });
    		
    	}
    	
    }
    
    /*function storeMap(dataList){
    	console.log(dataList);
    	var markers = new ol.layer.Marker( "Markers" );
    	map.addLayer(markers);

    	var size = new OpenLayers.Size(21,25);
    	var offset = new OpenLayers.Pixel(-(size.w/2), -size.h);
    	var icon = new OpenLayers.Icon('http://www.openlayers.org/dev/img/marker.png', size, offset);
    	markers.addMarker(new OpenLayers.Marker(new OpenLayers.LonLat(37.5325938,126.9878542),icon));
    	markers.addMarker(new OpenLayers.Marker(new OpenLayers.LonLat(37.5325938,126.9878542),icon.clone()));
    	
    	
    	
    }*/
    
   
    
    
    function initMap() {
    	
        initBaseMap();
        
        layerName = $("#mapList option:selected").val();
    	sourceName = $("#mapList option:selected").val();
    	hsourceName = 'H_' + $("#mapList option:selected").val();
     
        
        view = new ol.View({
            center: ol.proj.transform([128.0000,36.5000], 'EPSG:4326', 'EPSG:3857'),
            zoom: 8
        });

        
        
        map = new ol.Map({
            layers: [
                layers['base']
            ],
            interactions: ol.interaction.defaults({
                altShiftDragRotate: false,
                dragPan: false,
                rotate: false
            }).extend([new ol.interaction.DragPan({kinetic: null})]),
            target: 'map',
            renderer : 'canvas',
            view: view
        });
        map.addLayer(layers[layerName]);
        map.addLayer(layers[hsourceName]);
        layers[layerName].setVisible(true);
        map.on('singleclick', function(evt) {
        	console.log(evt);
        			/*var source = sources[sourceName];
        			var layer= layers[layerName];
        			var hsource = sources[hsourceName]
        			console.log(layerName);
        			console.log(source);
        			console.log(layer);
        			console.log(hsource);*/
        	if(layers['SELL_PLACE'].getVisible()){
        		 var feature = map.forEachFeatureAtPixel(evt.pixel, function (feature, layer) {
                     var prop = layer.getProperties();
                     layerNm = prop.name;
                     return feature;
                 });
            	if(feature){
            		console.log(feature);
            	
            		var prop = feature.getProperties();
                	if(feature.getGeometry().getCoordinates().length < 3 && feature.getGeometry().getCoordinates().length > 0){
                		if('EVENT_LVCD' in prop){}
                		else{
                            if(layerNm){
                            	console.log(layerNm);
                                gis.setUnselectPoint();
                                clickFeature.push({layerNm : layerNm, feature : feature});
                                feature.setStyle(getLayerPointHoverStyle(layerNm, feature));
                            }
                            view.setCenter(feature.getGeometry().getCoordinates());
                            console.log("view");
                            console.log(feature.getGeometry());
                            if(view.getZoom()<14){
                            	view.setZoom(14);
                            }
                            //getLayerFeatureInfo1(url, feature, evt.coordinate,BM00002);
                            $('#modalBody').html("");
                            
                            var p1 = $("<p>이름 : " + feature.P['SELL_PLACE_NAME'] +  "</p><br>");
                            var p2 = $("<p>주소 : " + feature.P['SELL_PLACE_ADDRESS'] +  "</p><br>");
                            var p3 = $("<p>판매물품 갯수 : " + feature.P['SELL_CNT'] +  "</p><br>");
                            var p4 = $("<p>평균 평점 : " + feature.P['REVIEW_RATING'] +  "</p><br>");
                            var bt = $("<input type = 'button' id = 'sellPlaceView' value='이동'>");
                            var btParam = $("<input type = 'hidden' id = 'sellPlaceViewParam' value='" + feature.P['SELL_PLACE_NO'] + "'>");
                        	p1.appendTo('#modalBody');
                        	p2.appendTo('#modalBody');
                        	p3.appendTo('#modalBody');
                        	p4.appendTo('#modalBody');
                        	bt.appendTo('#modalBody');
                        	btParam.appendTo('#modalBody');
                            
                            $('#myModal').modal('show');
                		}
                	}
            	}
        	}else{
        		        	
        		if(layers['SELL_SD'].getVisible()){
        			console.log("sido");
                	layer = layers['SELL_SD'];
                	source = sources['SELL_SD'];
                	hsource = sources['H_SELL_SD'];
            	}
            	else if(layers['SELL_SGG'].getVisible()){
            		console.log("sgg");
                	layer = layers['SELL_SGG'];
                	source = sources['SELL_SGG'];
                	hsource = sources['H_SELL_SGG'];
            	}
        	
        	
                	if(source && hsource && layer){
                    	var resolution = view.getResolution();
                    	var layerNm = layer.getProperties().name;
                    	var param;
                    	console.log(layer.getProperties().name);
                    	console.log(resolution);
                    	console.log(view.getProjection());
                    	
                    	
                    	if(layerName == 'SELL_SD'){
                    		param = {'INFO_FORMAT': 'application/json', 'propertyName' : 'PLACE,SELLES,SEQ,NAME'};
                    		
                    		
                    	}else if(layerName == 'SELL_SGG'){
                        	param = {'INFO_FORMAT': 'application/json', 'propertyName' : 'PLACE,SELLES,SEQ,NAME'};
                        	//sources[layerName].updateParams({VIEWPARAMS:'minYear:'+minYear+';maxYear:'+maxYear});
                    	}

                    	
                    	
                    
                    
                    	var url = source.getGetFeatureInfoUrl(evt.coordinate, resolution, view.getProjection(), param);
                    	getLayerFeatureInfo(url, layerNm, hsource, layer, evt.coordinate);
                    
                    	console.log(url);
                    	console.log(layerNm);
                    	console.log(hsource);
                    	console.log(layer);
                    	console.log("evt.coordinate : " + evt.coordinate);
                	}
        		}
        });
        
        
        
    }
    
    
    
    function getLayerFeatureInfo(url, layerNm, layerSource, layer, coord) {
        $.get(url, function (response) {
            console.log(response);
            var features = response.features;
            console.log(features[0].properties);
            
            
            $('#mtd').html('');
        	
        	var tr = $("<tr></tr>");
        	var td1 = $("<td scope='col'class='text-center'></th>");
        	td1.append(features[0].properties['NAME']);
        	td1.appendTo(tr);
        	var td2 = $("<td scope='col'class='text-center'></th>");
        	td2.append(features[0].properties['PLACE']);
        	td2.appendTo(tr);
        	var td3 = $("<td scope='col'class='text-center'></th>");
        	td3.append(features[0].properties['SELLES']);
        	td3.appendTo(tr);
        	
        	var td4 = $("<input type='hidden' value = " + features[0].properties['SEQ'] + ">");
        	
        	td4.appendTo(tr);
        	
        	

        	tr.appendTo('#mtd');
            console.log(layerSource);
            
            sources['H_SELL_SD'].updateParams({VIEWPARAMS:'code:' + features[0].properties['SEQ']});
        	layerSource.updateParams({VIEWPARAMS:'code:' + features[0].properties['SEQ']});
            
        });
    }
    

    
    function initBaseMap() {
        layers['base'] = new ol.layer.Tile({
            title : 'VWorld Map',
            visible : true,
            type : 'base',
            source : new ol.source.XYZ({url : BASE_MAP_URL})
        });
    }
    
    
    
    
    function mapChange2() {
        console.log('123');
    }

   //카테고리 변경시 맵 스타일 변경(작업중)
    function setVisibleSafeLayer(layerName) {
    	
    	var bg = $('#categoryList1 option:selected').val();
    	var md = $('#categoryList2 option:selected').val();
    	var sm = $('#categoryList3 option:selected').val();
    	console.log(bg);
    	console.log(md);
    	console.log(sm);
    	var category = '';
    	if(bg == 'all'){			//대분류가 전체일 경우
    		category = '______';
    	}else if(md == 'all'){		//대분류가 전체가 아니고 중분류가 전체일 때
    		category = bg  +'____';
    	}else if(sm == 'all'){		//대분류가 전체가 아니고 중분류가 전체가 아니고 소분류가 전체일 때
    		category = md + '__';
    	}else{						//모두 all이 아닐 때
    		category = sm;
    	}
    	
    	console.log(category);
    	
    	//sources[layerName].updateParams({VIEWPARAMS:'CATEGORY:'+category});
    	
    	
 
        
        
        /*var popup = document.getElementById("infoWindow");
        popup.style.display = 'none';*/


    	//map.removeLayer(layers[layerName]);
    	
    	
    	
        sources[layerName].updateParams({VIEWPARAMS:'category:'+category+';'});
        
        /*var SELL_SD = new ol.layer.Image({
            name: 'SELL_SD',
            source: sources[layerName],
            minResolution: 0,
            maxResolution: 100000000,
            //visible: true
        });*/
        
        
        
        //map.addLayer(SELL_SD);
        layers[layerName].setVisible(true);
        //SELL_SD.setVisible(true);
       
    }
 
   
      
    return {
    	//makeTest : makeTest,
        init : initMap,
        mapChange : mapChange,
        mapChange2 : mapChange2,
        setVisibleSafeLayer : setVisibleSafeLayer
        /*listFeatureInfo : getFeaturesProperties,
        clear : clearLayer,
        clearlayer2 :clearLayer2,
        search : setFeatures,
        page : featureList,
        move : moveToFeature,
        move2 : moveToFeature2,
        initSafeLayer: initSafeLayer,
        safeLayer : setVisibleSafeLayer,
        hideSafeLayer : hideSafeLayer,
        changeBasemap : changeBasemap,
        setHideHighlightLayer : setHideHighlightLayer,
        setKescoVisible : setKescoVisible*/
    }
    
    
    
}