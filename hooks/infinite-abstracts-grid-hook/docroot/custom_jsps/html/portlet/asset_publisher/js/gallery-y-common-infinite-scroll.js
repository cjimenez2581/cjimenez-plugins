YUI.add('gallery-y-common-infinite-scroll', function (Y, NAME) {


var EV_LOADING = 'infinite-scroll:loading'; /* event fired while requesting data */
var EV_FINISHED = 'infinite-scroll:finished'; /* event fired when data arrives */
var EV_NO_MORE_RESULTS = 'infinite-scroll:no-more-results'; /* event fired when there is no more data available to show */

Y.InfiniteScroll = Y.Base.create('gallery-y-common-infinite-scroll', Y.Base, [], {

    updateInitiated: false,

    currentOffset: null,

    allItemsFetched: false,

    initializer: function () {
        this.currentOffset = this.get('offset');
        if (this.get('initialize')) {
            this._onScroll();
        }
        Y.one(window).on('scroll', Y.bind(function () {
            this._onScroll();
        }, this));
    },

    _onScroll: function () {
        var me = this;
        
        var scrollPos = 0; // scroll bar position
        if (this.updateInitiated) {
            return;
        }

        var pageHeight = document.documentElement.scrollHeight; // document height
        var clientHeight = document.documentElement.clientHeight; // visible height

        if (Y.UA.ie) {
            scrollPos = document.documentElement.scrollTop;
        } else {
            scrollPos = window.pageYOffset;
        }

        if (pageHeight - (scrollPos + clientHeight) < this.get('scrollBufferSize')  && !this.allItemsFetched) {
            this.updateInitiated = true;
            var offset = this.get('container').all('.item').size();

            this.fire(EV_LOADING);
            document.documentElement.scrollTop += this.get('scrollBufferSize'); // move the scroll bar
            
            if (this.get('requestCustomData')) {
                Y.bind(this.get('requestCustomData'), this)(function(response) {
                    me._processResponse(response);
                    me.updateInitiated = false;
                });
            } else {
                this.requestItems();
            }

        }

    },
    
    requestItems: function() {
        var me = this;
        var url = Y.Lang.sub(this.get('dataSourceUrl'), {offset: this.currentOffset});
        var oConn = Y.io(url, {
            on: {
                success: function (id, o, args) {
                    var resp = Y.JSON.parse(o.responseText);
                    me._processResponse(resp);
                },
                complete: function (id, o, args) {
                    me.updateInitiated = false;
                }
            }
        });
    },
    
    _processResponse: function(response) {
        var me = this;
        me.currentOffset += me.get('offset');
        if (response.length > 0) {
        	
        	var itemIndex = me.get('container').all('.row-fluid').last()
        		.all(me.get('itemsSelector')).size();        	
        	var rowContainer = null;
        	
        	if(itemIndex % me.get('itemsPerRow') == 0){
        		rowContainer = Y.Node.create('<div class="row-fluid"></div>');
        		me.get('container').append(rowContainer);        		
        	}
        	else{
        		rowContainer = me.get('container').all('.row-fluid').last();
        	}
        	
            Y.Array.each(response, function (item) {            	
                item = me.get('itemPreProcessor')(item);                
                
                var itemProcessed = Y.Node.create(Y.Lang.sub(me.get('itemTemplate'), item));
                rowContainer.append(itemProcessed);

                itemProcessed.transition({
                    duration: 1,
                    opacity: 1                	
                });
                
                itemIndex++;               
            	if(itemIndex % me.get('itemsPerRow') == 0){
            		rowContainer = Y.Node.create('<div class="row-fluid"></div>');
            		me.get('container').append(rowContainer);
            	}
            });
        } else {
            me.fire(EV_NO_MORE_RESULTS);
            me.allItemsFetched = true;
        }
        me.fire(EV_FINISHED);
    }
}, {
    ATTRS: {
    	
    	/**
         * Custom EBH attr: number of items per row 
         * 
         */ 
        itemsPerRow: {
            value: 4
        },
        
        /**
         * Url template of the data source will be like domain.com?offset={offset}, will be used to make the ajax call
         */ 
        dataSourceUrl: {
            value: ''
        },
        
        /**
         * Infinite items Y.Node container
         */ 
        container: {
            value: null
        },
        
        /**
         * Items container selector
         */ 
        itemsSelector: {
            value: ''
        },
        
        /**
         * Html template to be used when ajax call arrives with the results
         */ 
        itemTemplate: {
            value: ''
        },
        
        /**
         * Number of items requested 
         * 
         */ 
        offset: {
            value: 15
        },
        
        /**
         * Requests data on initialization or only on scroll
         * 
         */ 
        initialize: {
            value: false
        },
        
        /**
         * Custom function to request items
         * @example 
         * function(callback) {} 
         *      @param Function callback Callback function to return items after items request is done
         *          @example function(items) { ... }
         */ 
        requestCustomData: {
            value: null
        },
        
        /**
         * Custom function to process each item data before rendering is going to be triggered on each item
         * @example 
         * function(item) { return item; } 
         */
        itemPreProcessor: {
            value: function(item) {
                return item;
            }
        },
        
        /**
         * Scroll buffer size to listen when moving the scroll and request more data
         * 
         */ 
        scrollBufferSize: {
            value: 60
        }
    }
});

}, '@VERSION@', {
    "requires": [
        "yui-base",
        "widget",
        "base",
        "node",
        "node-event-delegate",
        "event",
        "io-base",
        "json-parse",
        "transition"
    ]
});
