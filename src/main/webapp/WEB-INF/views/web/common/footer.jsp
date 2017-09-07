<%--
  Created by IntelliJ IDEA.
  User: 阿展
  Date: 2017-08-22
  Time: 16:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<footer style="clear: both;margin-bottom:0">
    <div class="footer-box">
        <div class="footer-box-left">
            <img src="<%=request.getContextPath()%>/assets/web/img/logowhite.png" alt="">
            <ul>
                <li>
                    <a href="${sysProp.aboutUs}">关于我们</a>
                </li>
            <c:forEach items="${sysProp.partnerList}" var="p" varStatus="i">
                <li>
                    <a href="${sysProp.partnerUrlList[i.index]}">${p}</a>
                </li>
            </c:forEach>
            </ul>
            <div id="horizontal-line"></div>
            <section style="text-align: right">
                <span style="text-align: justify;margin-right: 10px;color: #FFFFFF;display: inline-block"  >${sysProp.copyright}</span>
                <span style="margin-right: 10px;color: #FFFFFF;display: inline-block" > ${sysProp.recordNumber}</span>
                <span style="color: #FFFFFF;display: inline-block" >${sysProp.license}</span>
            </section>
        </div>
        <div class="footer-box-right">
            <img src="<%=request.getContextPath()%>/assets/web/img/codesmall.png" alt="">
            <ul>
                <li>
                    <div>
                        <img class="address-img" src="<%=request.getContextPath()%>/assets/web/img/phone.png" alt="">
                        <span>${sysProp.phone}</span>
                    </div>
                </li>
                <li>
                    <div>
                        <img class="address-img" src="<%=request.getContextPath()%>/assets/web/img/qq.png" alt="">
                        <span>${sysProp.qq}</span>
                    </div>
                </li>
                <li>
                    <div>
                        <img class="address-img" src="<%=request.getContextPath()%>/assets/web/img/mail.png" alt="">
                        <span>${sysProp.email}</span>
                    </div>
                </li>
                <li>
                    <div>
                        <img class="address-img" src="<%=request.getContextPath()%>/assets/web/img/address.png" alt="">
                        <span>${sysProp.address}</span>
                    </div>
                </li>
            </ul>
        </div>
    </div>
</footer>