(function()
{
    return function()
    {
        if (!this._is_form)
            return;
        
        var obj = null;
        
        this.on_create = function()
        {
            this.set_name("frameWork");
            this.set_titletext("frameWork");
            if (Form == this.constructor)
            {
                this._setFormPosition(1050,872);
            }
            
            // Object(Dataset, ExcelExportObject) Initialize

            
            // UI Components Initialize
            obj = new Static("Static03","1063","-1","33","22",null,null,null,null,null,null,this);
            obj.set_taborder("0");
            obj.set_text("h16");
            obj.set_visible("false");
            this.addChild(obj.name, obj);

            obj = new Div("divWork","0","32",null,null,"0","0",null,null,null,null,this);
            obj.set_taborder("1");
            obj.set_border("1px none , 1px none , 1px none, 1px none");
            obj.set_text("");
            this.addChild(obj.name, obj);

            obj = new Div("divTitle","0","0",null,"32","0",null,null,null,null,null,this);
            obj.set_taborder("2");
            obj.set_url("frame::frameWorkTitle.xfdl");
            obj.set_border("1px none , 1px none , 1px solid gainsboro");
            this.addChild(obj.name, obj);

            // Layout Functions
            //-- Default Layout : this
            obj = new Layout("default","",this._adjust_width,this._adjust_height,this,function(p){});
            this.addLayout(obj.name, obj);
            
            // BindItem Information

        };
        
        this.loadPreloadList = function()
        {
            this._addPreloadList("fdl","frame::frameWorkTitle.xfdl");
        };
        
        // User Script
        this.addIncludeScript("frameWork.xfdl","lib::cmmInclude.xjs");
        this.registerScript("frameWork.xfdl", function() {
        /***********************************************************************************
        * 01. 업무구분 :
        * 02. 메뉴명   :
        * 03. 메뉴설명 :
        * 04. 작성일   :
        * 05. 작성자   :
        * 06. 수정이력 :
        ***********************************************************************************
        *     수정일     작성자   내용
        ***********************************************************************************
        *
        ***********************************************************************************/

        /***********************************************************************************
        * include 선언부  													               *
        ***********************************************************************************/
        this.executeIncludeScript("lib::cmmInclude.xjs"); /*include "lib::cmmInclude.xjs"*/;

        /***********************************************************************************/
        /* 폼 전역변수 선언부												               */
        /***********************************************************************************/
        this.objApp;
        /***********************************************************************************
        * Form Event
        ***********************************************************************************/
        /**
         * form onload 함수
         * @return
         * @example
         * @memberOf
         */
        this.form_onload = function(obj,e)
        {
        	this.objApp = nexacro.getApplication() ;

        	//넘어온 아규먼트 셋팅
        	this.fnSetOwnFrameArgu(this);

        	// form로딩시 화면에 표시할 page URL 설정
        	this.divWork.set_url(this.objApp.gvWorkPageUrl);
        	this.fnOnsize();

        	this.divTitle.form.set_scrollbartype("none")
        };

        /**
         *  넘어온 아규먼트 처리 설정
         * @param  {XaComp} this(현재form)
         * @return
         * @example
         * fnSetOwnFrameArgu(this);
         * @memberOf
         */
        this.fnSetOwnFrameArgu = function(o)
        {
        	this.objApp.gvWorkWinkey  = o.getOwnerFrame().arguments["winKey"];
        	this.objApp.gvWorkPageUrl = o.getOwnerFrame().arguments["pageUrl"];
        	this.objApp.gvWorkMenuNm  = o.getOwnerFrame().arguments["menuNm"];
        	this.objApp.gvWorkMenuId  = o.getOwnerFrame().arguments["menuId"];
        	this.objApp.gvWorkTitle   = this.objApp.gvWorkMenuNm+" ("+ this.objApp.gvWorkMenuId+")";

        	var menuId = o.getOwnerFrame().arguments["menuId"];

        	if(this.objApp.gvIsComBtnUse){	//공통 버튼 사용유무
        		//공통 버튼권한세팅
        		var sAuth = this.fnGetAuth(menuId, "auth");  //권한조회
        		this.divTitle.form.fnSetAuthBtn(sAuth);
        	}

        	var sTitle = o.getOwnerFrame().arguments["menuNm"];
        	this.divTitle.form.staTitle.set_text(sTitle);

        	//마이메뉴 등록확인
        }

        /**
         * workFrame_onactivate event
         * @return
         * @example
         *
         * @memberOf
         */
        this.form_onactivate = function(obj,e)
        {
        	var winKey = this.objApp.gvWorkFrame.getActiveFrame().name;
        	//this.objApp.gvWorkFrame.frames[winKey].setFocus();

        	var gRow = this.objApp.gdsOpenMenu.findRow(this.objApp.gvMenuColumns.winId, winKey);

        	var dsLMenu = this.objApp.gvLeftFrame.form.dsMenu;
        	var menuId = this.objApp.gdsOpenMenu.getColumn(gRow, this.objApp.gvMenuColumns.menuId);
        	var lRow = this.objApp.gvLeftFrame.form.dsMenu.findRow(this.objApp.gvMenuColumns.menuId, menuId);

        	//메뉴 타이틀
        	var sTitle = this.objApp.gdsOpenMenu.getColumn(gRow, "title");
        	this.divTitle.form.staTitle.set_text(sTitle);

        	// gdsOpenMenu row 이동
        	this.objApp.gdsOpenMenu.set_rowposition(gRow);
        };

        /**
         * gdsMenu에서 버튼권한 가져오기
         * @param {string} menuId: 메뉴Id
         * @param {string} colId: 컬럼(권한별) Id
         * @return
         * @example
         *
         * @memberOf
         */
        this.fnGetAuth = function(menuId,colId)
        {
        	var sValue = this.objApp.gdsMenu.lookupAs(this.objApp.gvMenuColumns.menId, menuId, colId);
        	if(this.gfnIsNull(sValue))
        	{
        		sValue = "YNNNNN";
        	}
        	return sValue;
        }

        /**
         * workFrame_onclose event
         * @return
         * @example
         *
         * @memberOf
         */
        this.form_onclose = function(obj,e)
        {
        	try{
        	//trace("workFrame_onclose : " + e.fromobject.name);
        		if( e.fromobject == "[object Form]" ){
        			var objApp = nexacro.getApplication();
        			var winId = this.getOwnerFrame().name;
        			//trace("winId : " + winId);
        			if( objApp.gvMdiFrame.form.bClose ) {
        				objApp.gvMdiFrame.form.bClose = false;
        				objApp.gvMdiFrame.form.fnTabOnClose(winId);
        			}
        		}
        	}catch(e){

        	}
        }

        /**
         * work resize event
         * @return
         * @example
         *
         * @memberOf this
         */
        this.form_onsize = function(obj,e)
        {
        	this.fnOnsize();
        }

        this.fnOnsize = function()
        {
            this.resetScroll();
        }

         /**
         * @description 각 화면에서 디버그창을 호출할 수 있도록 단축키 지정
        */
        this.form_onkeydown = function(obj,e)
        {
        	//trace("e.ctrlkey : " + e.ctrlkey + " / e.keycode : " + e.keycode);
        	if (e.ctrlkey && e.keycode == 68)
        	{
        		var oArg = {};
        		var oOption = {popuptype:"modeless"};	//top, left를 지정하지 않으면 가운데정렬 //top:20,left:370
        		var sPopupCallBack = "fnPopupCallback";
        		this.gfnOpenPopup( "debug","cmm::cmmDebug.xfdl",oArg,sPopupCallBack,oOption);
        	}
        };

        this.form_onbeforeclose = function(obj,e)
        {

        };

        this.fnWorkFrameClose = function()
        {
        	//this.gfn_formClose();
        	if(this.gfnIsNull(this.divWork.form.lookup("fnClose")))
        	{
        		trace("fnClose 함수가 없습니다.");
        	}else{
        		if(this.divWork.form.fnClose() == true)
        		{
        			var sMsgId = "confirm.before.movepage";							//메세지ID
        			var arrArg = "";												//메세지취환될값 배열[생략가능]
        			var sPopId = sMsgId;											//메세지팝업ID[생략가능]	*해당화면에서 메시지 중복사용시 구분되는값을 넣어줘야함
        			var sMsgCallback = "fnMsgCallback";								//메세지콜백[생략가능] 		* confirm성 메시지를 사용 시 반드시 필요

        			// 변경된 내역을 저장 하시겠습니까?
        			this.gfnAlert(sMsgId, arrArg, sPopId, sMsgCallback);
        			return false;
        		}
        	}
        	this.close();
        }

         /**
         * @description 메세지 콜백
        */
        this.fnMsgCallback = function (strId,strVal)
        {
        	//trace("strId >> " + strId + "   strVal >> " + strVal);
        	if(strId == "confirm.before.movepage"){
        		if(strVal == true)
        		{
        			this.close();
        		}
        	}
        };

        });
        
        // Regist UI Components Event
        this.on_initEvent = function()
        {
            this.addEventHandler("onclose",this.form_onclose,this);
            this.addEventHandler("onsize",this.form_onsize,this);
            this.addEventHandler("onactivate",this.form_onactivate,this);
            this.addEventHandler("onload",this.form_onload,this);
            this.addEventHandler("onkeydown",this.form_onkeydown,this);
            this.addEventHandler("onbeforeclose",this.form_onbeforeclose,this);
        };

        this.loadIncludeScript("frameWork.xfdl");
        this.loadPreloadList();
        
        // Remove Reference
        obj = null;
    };
}
)();
