!function(j) {
    var i = {
            path: "",
            defSkin: "danlan",
            format: "YYYY-MM-DD",
            min: "1900-01-01 00:00:00",
            max: "2099-12-31 23:59:59",
            isv: !1
        },
        p = {},
        o = document,
        n = "createElement",
        m = "getElementById",
        l = "getElementsByTagName",
        k = ["laydate_box", "laydate_void", "laydate_click", "LayDateSkin", "skins/", "/laydate.css"];
    j.laydate = function(a) {
        a = a || {};
        try {
            k.event = j.event ? j.event : laydate.caller.arguments[0]
        } catch (c) {}
        return p.run(a), laydate
    }, laydate.v = "1.1", p.getPath = function() {
        var b = document.scripts,
            d = b[b.length - 1].src;
        return i.path ? i.path : d.substring(0, d.lastIndexOf("/") + 1)
    }(), p.use = function(d, c) {
        var e = o[n]("link");
        e.type = "text/css", e.rel = "stylesheet", e.href = p.getPath + d + k[5], c && (e.id = c), o[l]("head")[0].appendChild(e), e = null
    }, p.trim = function(b) {
        return b = b || "", b.replace(/^\s|\s$/g, "").replace(/\s+/g, " ")
    }, p.digit = function(b) {
        return 10 > b ? "0" + (0 | b) : b
    }, p.stopmp = function(a) {
        return a = a || j.event, a.stopPropagation ? a.stopPropagation() : a.cancelBubble = !0, this
    }, p.each = function(f, e) {
        for (var h = 0, g = f.length; g > h && e(h, f[h]) !== !1; h++) {}
    }, p.hasClass = function(d, c) {
        return d = d || {}, new RegExp("\\b" + c + "\\b").test(d.className)
    }, p.addClass = function(d, c) {
        return d = d || {}, p.hasClass(d, c) || (d.className += " " + c), d.className = p.trim(d.className), this
    }, p.removeClass = function(e, c) {
        if (e = e || {}, p.hasClass(e, c)) {
            var f = new RegExp("\\b" + c + "\\b");
            e.className = e.className.replace(f, "")
        }
        return this
    }, p.removeCssAttr = function(e, d) {
        var f = e.style;
        f.removeProperty ? f.removeProperty(d) : f.removeAttribute(d)
    }, p.shde = function(d, c) {
        d.style.display = c ? "none" : "block"
    }, p.query = function(d) {
        var r, c, q, g, f;
        return d = p.trim(d).split(" "), c = o[m](d[0].substr(1)), c ? d[1] ? /^\./.test(d[1]) ? (g = d[1].substr(1), f = new RegExp("\\b" + g + "\\b"), r = [], q = o.getElementsByClassName ? c.getElementsByClassName(g) : c[l]("*"), p.each(q, function(h, e) {
            f.test(e.className) && r.push(e)
        }), r[0] ? r : "") : (r = c[l](d[1]), r[0] ? c[l](d[1]) : "") : c : void 0
    }, p.on = function(a, f, c) {
        return a.attachEvent ? a.attachEvent("on" + f, function() {
            c.call(a, j.even)
        }) : a.addEventListener(f, c, !1), p
    }, p.stopMosup = function(d, c) {
        "mouseup" !== d && p.on(c, "mouseup", function(b) {
            p.stopmp(b)
        })
    }, p.run = function(h) {
        var u, t, r, c = p.query,
            s = k.event;
        try {
            r = s.target || s.srcElement || {}
        } catch (q) {
            r = {}
        }
        if (u = h.elem ? c(h.elem) : r, s && r.tagName) {
            if (!u || u === p.elem) {
                return
            }
            p.stopMosup(s.type, u), p.stopmp(s), p.view(u, h), p.reshow()
        } else {
            t = h.event || "click", p.each((0 | u.length) > 0 ? u : [u], function(a, e) {
                p.stopMosup(t, e), p.on(e, t, function(d) {
                    p.stopmp(d), e !== p.elem && (p.view(e, h), p.reshow())
                })
            })
        }
    }, p.scroll = function(b) {
        return b = b ? "scrollLeft" : "scrollTop", o.body[b] | o.documentElement[b]
    }, p.winarea = function(b) {
        return document.documentElement[b ? "clientWidth" : "clientHeight"]
    }, p.isleap = function(b) {
        return 0 === b % 4 && 0 !== b % 100 || 0 === b % 400
    }, p.checkVoid = function(f, c, h) {
        var g = [];
        return f = 0 | f, c = 0 | c, h = 0 | h, f < p.mins[0] ? g = ["y"] : f > p.maxs[0] ? g = ["y", 1] : f >= p.mins[0] && f <= p.maxs[0] && (f == p.mins[0] && (c < p.mins[1] ? g = ["m"] : c == p.mins[1] && h < p.mins[2] && (g = ["d"])), f == p.maxs[0] && (c > p.maxs[1] ? g = ["m", 1] : c == p.maxs[1] && h > p.maxs[2] && (g = ["d", 1]))), g
    },p.timeVoid = function(d, c) {
        if (p.ymd[1] + 1 == p.mins[1] && p.ymd[2] == p.mins[2]) {
            if (0 === c && d < p.mins[3]) {
                return 1
            }
            if (1 === c && d < p.mins[4]) {
                return 1
            }
            if (2 === c && d < p.mins[5]) {
                return 1
            }
        } else {
            if (p.ymd[1] + 1 == p.maxs[1] && p.ymd[2] == p.maxs[2]) {
                if (0 === c && d > p.maxs[3]) {
                    return 1
                }
                if (1 === c && d > p.maxs[4]) {
                    return 1
                }
                if (2 === c && d > p.maxs[5]) {
                    return 1
                }
            }
        }
        return d > (c ? 59 : 23) ? 1 : void 0
    }, p.check = function() {
        var g = p.options.format.replace(/YYYY|MM|DD|hh|mm|ss/g, "\\d+\\").replace(/\\$/g, ""),
            c = new RegExp(g),
            r = p.elem[k.elemv],
            q = r.match(/\d+/g) || [],
            h = p.checkVoid(q[0], q[1], q[2]);
        if ("" !== r.replace(/\s/g, "")) {
            if (!c.test(r)) {
                return p.elem[k.elemv] = "", p.msg("??????????????????????????????????????????"), 1
            }
            if (h[0]) {
                return p.elem[k.elemv] = "", p.msg("?????????????????????????????????????????????"), 1
            }
            h.value = p.elem[k.elemv].match(c).join(), q = h.value.match(/\d+/g), q[1] < 1 ? (q[1] = 1, h.auto = 1) : q[1] > 12 ? (q[1] = 12, h.auto = 1) : q[1].length < 2 && (h.auto = 1), q.length > 2 && (q[2] < 1 ? (q[2] = 1, h.auto = 1) : q[2] > p.months[(0 | q[1]) - 1] ? (q[2] = 31, h.auto = 1) : q[2].length < 2 && (h.auto = 1)), q.length > 3 && (p.timeVoid(q[3], 0) && (h.auto = 1), p.timeVoid(q[4], 1) && (h.auto = 1), p.timeVoid(q[5], 2) && (h.auto = 1))&& (h.auto = 1)), h.auto ? (q.length > 2 ? p.creation([q[0], 0 | q[1], 0 | q[2]], 1) : p.creation([q[0], 0 | q[1], 0], 1)) : h.value !== p.elem[k.elemv] && (p.elem[k.elemv] = h.value)
        }
    }, p.months = [31, null, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31], p.viewDate = function(e, c, r) {
        var q = (p.query, {}),
            h = new Date;
        e < (0 | p.mins[0]) && (e = 0 | p.mins[0]), e > (0 | p.maxs[0]) && (e = 0 | p.maxs[0]), h.setFullYear(e, c, r), q.ymd = [h.getFullYear(), h.getMonth(), h.getDate()], p.months[1] = p.isleap(q.ymd[0]) ? 29 : 28, h.setFullYear(q.ymd[0], q.ymd[1], 1), q.FDay = h.getDay(), q.PDay = p.months[0 === c ? 11 : c - 1] - q.FDay + 1, q.NDay = 1, p.each(k.tds, function(s, f) {
            var t, v = q.ymd[0],
                u = q.ymd[1] + 1;
            f.className = "", s < q.FDay ? (f.innerHTML = t = s + q.PDay, p.addClass(f, "laydate_nothis"), 1 === u && (v -= 1), u = 1 === u ? 12 : u - 1) : s >= q.FDay && s < q.FDay + p.months[q.ymd[1]] ? (f.innerHTML = t = s - q.FDay + 1, s - q.FDay + 1 === q.ymd[2] && (p.addClass(f, k[2]), q.thisDay = f)) : (f.innerHTML = t = q.NDay++, p.addClass(f, "laydate_nothis"), 12 === u && (v += 1), u = 12 === u ? 1 : u + 1), p.checkVoid(v, u, t)[0] && p.addClass(f, k[1]), p.options.festival && p.festival(f, u + "." + t), f.setAttribute("y", v), f.setAttribute("m", u), f.setAttribute("d", t), v = u = t = null
        }), p.valid = !p.hasClass(q.thisDay, k[1]), p.ymd = q.ymd, k.year.value = p.ymd[0] + "???", k.month.value = p.digit(p.ymd[1] + 1) + "???", p.each(k.mms, function(g, f) {
            var s = p.checkVoid(p.ymd[0], (0 | f.getAttribute("m")) + 1);
            "y" === s[0] || "m" === s[0] ? p.addClass(f, k[1]) : p.removeClass(f, k[1]), p.removeClass(f, k[2]), s = null
        }), p.addClass(k.mms[p.ymd[1]], k[2]), q.times = [0 | p.inymd[3] || 0, 0 | p.inymd[4] || 0, 0 | p.inymd[5] || 0], p.each(new Array(3), function(b) {
            p.hmsin[b].value = p.digit(p.timeVoid(q.times[b], b) ? 0 | p.mins[b + 3] : 0 | q.times[b])
        }), p[p.valid ? "removeClass" : "addClass"](k.ok, k[1])
    }, p.festival = function(e, d) {
        var f;
        switch (d) {
            case "1.1":
                f = "??????";
                break;
            case "3.8":
                f = "??????";
                break;
            case "4.5":
                f = "??????";
                break;
            case "5.1":
                f = "??????";
                break;
            case "6.1":
                f = "??????";
                break;
            case "9.10":
                f = "??????";
                break;
            case "10.1":
                f = "??????"
        }
        f && (e.innerHTML = f), f = null
    }, p.viewYears = function(e) {
        var c = p.query,
            f = "";
        p.each(new Array(14), function(a) {
            f += 7 === a ? "<li " + (parseInt(k.year.value) === e ? 'class="' + k[2] + '"' : "") + ' y="' + e + '">' + e + "???</li>" : '<li y="' + (e - 7 + a) + '">' + (e - 7 + a) + "???</li>"
        }), c("#laydate_ys").innerHTML = f, p.each(c("#laydate_ys li"), function(g, d) {
            "y" === p.checkVoid(d.getAttribute("y"))[0] ? p.addClass(d, k[1]) : p.on(d, "click", function(b) {
                p.stopmp(b).reshow(), p.viewDate(0 | this.getAttribute("y"), p.ymd[1], p.ymd[2])
            })
        })
    }, p.initDate = function() {
        var b = (p.query, new Date),
            a = p.elem[k.elemv].match(/\d+/g) || [];
        a.length < 3 && (a.length == 2 ? a.push("01") : a = p.options.start.match(/\d+/g) || [], a.length < 3 && (a = [b.getFullYear(), b.getMonth() + 1, b.getDate()])), p.inymd = a, p.viewDate(a[0], a[1] - 1, a[2])
    }, p.iswrite = function() {
        var d = p.query,
            c = {
                time: d("#laydate_hms")
            };
        var e = p.options.isym || false;
        p.shde(d("#laydate_table"), e);
        p.shde(c.time, !p.options.istime), p.shde(k.oclear, !("isclear" in p.options ? p.options.isclear : 1)), p.shde(k.otoday, !("istoday" in p.options ? p.options.istoday : 1)), p.shde(k.ok, !("issure" in p.options ? p.options.issure : 1))
    }, p.orien = function(f, c) {
        var h, g = p.elem.getBoundingClientRect();
        f.style.left = g.left + (c ? 0 : p.scroll(1)) + "px", h = g.bottom + f.offsetHeight / 1.5 <= p.winarea() ? g.bottom - 1 : (g.top > f.offsetHeight / 1.5 ? g.top - f.offsetHeight + 1 : p.winarea() - f.offsetHeight) + (p.options.isym || false) ? 150 : 0, f.style.top = h + (c ? 0 : p.scroll()) + "px"
    }, p.follow = function(b) {
        p.options.fixed ? (b.style.position = "fixed", p.orien(b, 1)) : (b.style.position = "absolute", p.orien(b))
    }, p.viewtb = function() {
        var d, c = [],
            r = ["???", "???", "???", "???", "???", "???", "???"],
            q = {},
            g = o[n]("table"),
            e = o[n]("thead");
        return e.appendChild(o[n]("tr")), q.creath = function(h) {
            var f = o[n]("th");
            f.innerHTML = r[h], e[l]("tr")[0].appendChild(f), f = null
        }, p.each(new Array(6), function(a) {
            c.push([]), d = g.insertRow(0), p.each(new Array(7), function(b) {
                c[a][b] = 0, 0 === a && q.creath(b), d.insertCell(b)
            })
        }), g.insertBefore(e, g.children[0]), g.id = g.className = "laydate_table", d = c = null, g.outerHTML.toLowerCase()
    }(), p.view = function(b, h) {
        var d, e = p.query,
            c = {};
        h = h || b, p.elem = b, p.options = h, p.options.format || (p.options.format = i.format),p.options.week = p.options.week || "", p.options.start = p.options.start || "", p.mm = c.mm = [p.options.min || i.min, p.options.max || i.max], p.mins = c.mm[0].match(/\d+/g), p.maxs = c.mm[1].match(/\d+/g), k.elemv = /textarea|input/.test(p.elem.tagName.toLocaleLowerCase()) ? "value" : "innerHTML", p.box ? p.shde(p.box) : (d = o[n]("div"), d.id = k[0], d.className = k[0], d.style.cssText = "position: absolute;", d.setAttribute("name", "laydate-v" + laydate.v), d.innerHTML = c.html = '<div class="laydate_top"><div class="laydate_ym laydate_y" id="laydate_YY"><a class="laydate_choose laydate_chprev laydate_tab"><cite></cite></a><input id="laydate_y" readonly><label></label><a class="laydate_choose laydate_chnext laydate_tab"><cite></cite></a><div class="laydate_yms"><a class="laydate_tab laydate_chtop"><cite></cite></a><ul id="laydate_ys"></ul><a class="laydate_tab laydate_chdown"><cite></cite></a></div></div><div class="laydate_ym laydate_m" id="laydate_MM"><a class="laydate_choose laydate_chprev laydate_tab"><cite></cite></a><input id="laydate_m" readonly><label></label><a class="laydate_choose laydate_chnext laydate_tab"><cite></cite></a><div class="laydate_yms" id="laydate_ms">' + function() {
            var f = "";
            return p.each(new Array(12), function(a) {
                f += '<span m="' + a + '">' + p.digit(a + 1) + "???</span>"
            }), f
        }() + "</div>" + "</div>" + "</div>" + p.viewtb + '<div class="laydate_bottom">' + '<ul id="laydate_hms">' + '<li class="laydate_sj">??????</li>' + "<li><input readonly>:</li>" + "<li><input readonly>:</li>" + "<li><input readonly></li>" + "</ul>" + '<div class="laydate_time" id="laydate_time"></div>' + '<div class="laydate_btn">' + '<a id="laydate_clear">??????</a>' + '<a id="laydate_today">??????</a>' + '<a id="laydate_ok">??????</a>' + "</div>" + (i.isv ? '<a href="http://sentsin.com/layui/laydate/" class="laydate_v" target="_blank">laydate-v' + laydate.v + "</a>" : "") + "</div>", o.body.appendChild(d), p.box = e("#" + k[0]), p.events(), d = null), p.follow(p.box), h.zIndex ? p.box.style.zIndex = h.zIndex : p.removeCssAttr(p.box, "z-index"), p.stopMosup("click", p.box), p.initDate(), p.iswrite(), p.check(),
        p.checkWeek = function(){
        	if(p.options.week==true){
        		$("#laydate_table tbody tr td").each(function(i,v){
    				var result=i%7;
    				if(result!=0){
    					$(v).addClass("laydate_void");
    				}
    			});
        	}
        }		
    }, p.reshow = function() {
        return p.each(p.query("#" + k[0] + " .laydate_show"), function(d, c) {
            p.removeClass(c, "laydate_show")
        }), this
    }, p.close = function() {
        p.reshow(), p.shde(p.query("#" + k[0]), 1), p.elem = null
    }, p.parse = function(b, f, c) {
        return b = b.concat(f), c = c || (p.options ? p.options.format : i.format), c.replace(/YYYY|MM|DD|hh|mm|ss/g, function() {
            return b.index = 0 | ++b.index, p.digit(b[b.index])
        })
    }, p.creation = function(d, c) {
        var h = (p.query, p.hmsin),
            g = p.parse(d, [h[0].value, h[1].value, h[2].value]);
        p.elem[k.elemv] = g, c || (p.close(), "function" == typeof p.options.choose && p.options.choose(g))
    }, p.events = function() {
        var a = p.query,
            c = {
                box: "#" + k[0]
            };
        p.addClass(o.body, "laydate_body"), k.tds = a("#laydate_table td"), k.mms = a("#laydate_ms span"), k.year = a("#laydate_y"), k.month = a("#laydate_m"), p.each(a(c.box + " .laydate_ym"), function(e, d) {
            p.on(d, "click", function(f) {
                p.stopmp(f).reshow(), p.addClass(this[l]("div")[0], "laydate_show"), e || (c.YY = parseInt(k.year.value), p.viewYears(c.YY))
            })
        }), p.on(a(c.box), "click", function() {
            p.reshow()
        }), c.tabYear = function(b) {
            0 === b ? p.ymd[0]-- : 1 === b ? p.ymd[0]++ : 2 === b ? c.YY -= 14 : c.YY += 14, 2 > b ? (p.viewDate(p.ymd[0], p.ymd[1], p.ymd[2]), p.reshow()) : p.viewYears(c.YY)
        }, p.each(a("#laydate_YY .laydate_tab"), function(e, d) {
            p.on(d, "click", function(f) {
                p.stopmp(f), c.tabYear(e)
            })
        }), c.tabMonth = function(b) {
            b ? (p.ymd[1]++, 12 === p.ymd[1] && (p.ymd[0]++, p.ymd[1] = 0)) : (p.ymd[1]--, -1 === p.ymd[1] && (p.ymd[0]--, p.ymd[1] = 11)), p.viewDate(p.ymd[0], p.ymd[1], p.ymd[2])
        }, p.each(a("#laydate_MM .laydate_tab"), function(e, d) {
            p.on(d, "click", function(f) {
                p.stopmp(f).reshow(), c.tabMonth(e)
            })
        }), p.each(a("#laydate_ms span"), function(e, d) {
            p.on(d, "click", function(b) {
                p.stopmp(b).reshow(), p.hasClass(this, k[1]) || p.viewDate(p.ymd[0], 0 | this.getAttribute("m"), p.ymd[2])
            })
        }), p.each(a("#laydate_table td"), function(e, d) {
            p.on(d, "click", function(b) {
                p.hasClass(this, k[1]) || (p.stopmp(b), p.creation([0 | this.getAttribute("y"), 0 | this.getAttribute("m"), 0 | this.getAttribute("d")]))
            })
        }), k.oclear = a("#laydate_clear"), p.on(k.oclear, "click", function() {
            p.elem[k.elemv] = "", p.close()
        }), k.otoday = a("#laydate_today"), p.on(k.otoday, "click", function() {
            p.elem[k.elemv] = laydate.now(0, p.options.format), p.close()
        }), k.ok = a("#laydate_ok"), p.on(k.ok, "click", function() {
            p.valid && p.creation([p.ymd[0], p.ymd[1] + 1, p.ymd[2]])
        }), c.times = a("#laydate_time"), p.hmsin = c.hmsin = a("#laydate_hms input"), c.hmss = ["??????", "??????", "??????"], c.hmsarr = [], p.msg = function(b, g) {
            var e = '<div class="laydte_hsmtex">' + (g || "??????") + "<span>??</span></div>";
            "string" == typeof b ? (e += "<p>" + b + "</p>", p.shde(a("#" + k[0])), p.removeClass(c.times, "laydate_time1").addClass(c.times, "laydate_msg")) : (c.hmsarr[b] ? e = c.hmsarr[b] : (e += '<div id="laydate_hmsno" class="laydate_hmsno">', p.each(new Array(0 === b ? 24 : 60), function(d) {
                e += "<span>" + d + "</span>"
            }), e += "</div>", c.hmsarr[b] = e), p.removeClass(c.times, "laydate_msg"), p[0 === b ? "removeClass" : "addClass"](c.times, "laydate_time1")), p.addClass(c.times, "laydate_show"), c.times.innerHTML = e
        }, c.hmson = function(b, q) {
            var h = a("#laydate_hmsno span"),
                g = p.valid ? null : 1;
            p.each(h, function(d, f) {
                g ? p.addClass(f, k[1]) : p.timeVoid(d, q) ? p.addClass(f, k[1]) : p.on(f, "click", function() {
                    p.hasClass(this, k[1]) || (b.value = p.digit(0 | this.innerHTML))
                })
            }), p.addClass(h[0 | b.value], "laydate_click")
        }, p.each(c.hmsin, function(e, d) {
            p.on(d, "click", function(f) {
                p.stopmp(f).reshow(), p.msg(e, c.hmss[e]), c.hmson(this, e)
            })
        }), p.on(o, "mouseup", function() {
            var b = a("#" + k[0]);
            b && "none" !== b.style.display && (p.check() || p.close())
        }).on(o, "keydown", function(e) {
            e = e || j.event;
            var f = e.keyCode;
            13 === f && p.creation([p.ymd[0], p.ymd[1] + 1, p.ymd[2]])
        })
    }, p.init = function() {
        p.use("need"), p.use(k[4] + i.defSkin, k[3]), p.skinLink = p.query("#" + k[3])
    }(), laydate.reset = function() {
        p.box && p.elem && p.follow(p.box)
    }, laydate.now = function(e, c) {
        var f = new Date(0 | e ? function(b) {
            return 86400000 > b ? +new Date + 86400000 * b : b
        }(parseInt(e)) : +new Date);
        return p.parse([f.getFullYear(), f.getMonth() + 1, f.getDate()], [f.getHours(), f.getMinutes(), f.getSeconds()], c)
    }, laydate.skin = function(b) {
        p.skinLink.href = p.getPath + k[4] + b + k[5]
    }
}(window);