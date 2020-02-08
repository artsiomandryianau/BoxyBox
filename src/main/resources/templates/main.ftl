<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>


<a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
Add new Delivery
</a>
<div class="collapse" id="collapseExample">
<div class="form-group mt-3">
    <form method="post" enctype="multipart/form-data">

        <div class="form-group">
            <input type="text" class="form-control" name="nameOfCommodity" placeholder="Name of commodity">
        </div>
        <div class="form-group">
            <input type="text" class="form-control" name="dateOfDelivery" placeholder="dateOfDelivery">
        </div>
        <div class="form-group">
            <input type="text" class="form-control" name="addressCity" placeholder="addressCity">
        </div>
        <div class="form-group">
            <input type="text" class="form-control" name="addressStreet" placeholder="addressStreet">
        </div>
        <div class="form-group">
            <input type="text" class="form-control" name="addressNumberOfBuilding" placeholder="addressNumberOfBuilding">
        </div>
        <div class="form-group">
            <input type="text" class="form-control" name="addressNumberOfApartament" placeholder="addressNumberOfApartament">
        </div>
        <div class="form-group">
            <input type="text" class="form-control" name="commodityPrise" placeholder="commodityPrise">
        </div>

        <div class="form-group">
            <div class="custom-file">
                <input type="file" name="file" id="file">
                <label class="custom-file-label" for="file">Choose file</label>
            </div>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <div class="form-group">
            <button type="submit" class="btn btn-primary">Add</button>
        </div>
    </form>
</div>
</div>






<div class="card-columns">

<#list deliveries as delivery>
<div class="card my-3">
    <#if delivery.filename??>
    <img src="/img/${delivery.filename}" class="card-img-top">
    </#if>
    <div class="m-2">
        <span>${delivery.nameOfCommodity}</span>
        <i align="right">${delivery.commodityPrise}</i>
    </div>
    <div class="card-footer text-muted">
        ${delivery.id}
        ${delivery.authorName}
        ${delivery.dateOfOrdering}
    </div>
    <div class="card-footer text-muted">
        ${delivery.dateOfDelivery}
        ${delivery.address}
    </div>
    <div class="card-footer text-muted">${delivery.isDelivered}
    </div>

    <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample1" role="button" aria-expanded="false" aria-controls="collapseExample1">
        Edit delivery
    </a>
    <div class="collapse" id="collapseExample1">
        <form method="post" action="delete/${delivery.id}">
            <input type="submit" href="delete/${delivery.id}" value="Delete"/>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
        </form>

        <form method="post" action="editD/${delivery.id}">
            <input type="text" class="form-control" name="newDate" placeholder="New date of delivery">
            <input type="submit" href="edit/${delivery.id}" value="Edit"/>

            <input type="hidden" name="_csrf" value="${_csrf.token}" />
        </form>
        <form method="post" action="editA/${delivery.id}">
            <input type="text" class="form-control" name="newAddress" placeholder="New address of delivery">
            <input type="submit" href="edit/${delivery.id}" value="Edit"/>

            <input type="hidden" name="_csrf" value="${_csrf.token}" />
        </form>
        <form method="post" action="editI/${delivery.id}">

            <input type="submit" href="edit/${delivery.id}" value="Is delivered"/>

            <input type="hidden" name="_csrf" value="${_csrf.token}" />
        </form>

    </div>

</div>

<#else>
No deliveries
</#list>
        </div>


        </@c.page>