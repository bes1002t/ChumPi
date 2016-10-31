interface OrdersByProduct {
    [product: string]: number;
}

interface ProductOrders {
    product: string;
    count: number;
}

class CoffeeStatsController {
    views = [ "top_alltime", "top_month", "hourly" ];
    view = "top_alltime";

    topAllTime: ProductOrders[];
    topMonth: ProductOrders[];

    private PRODUCTS = {
        // TODO: Sync with actual buttons
        "4": "Coffee, Black",
        "5": "Coffee with Whitener",
        "6": "Coffee with Sugar",
        "7": "Coffee with Whitener and Sugar",
        "8": "Latte Macchiato",
        "9": "Cappucino",
        "10": "Tea",
        "11": "Hot Chocolate",
        "12": "Malt Coffee",
        "13": "Vanilla Milk",
        "14": "Whatever"
    }

    constructor(private $scope: ng.IScope) {
        this.changeView();
    }

    nextView() {
        let i = this.views.indexOf(this.view) + 1;
        this.view = this.views[i % this.views.length];
        this.changeView();
    }

    prevView() {
        let i = this.views.indexOf(this.view) + this.views.length - 1;
        this.view = this.views[i  % this.views.length];
        this.changeView();
    }

    changeView() {
        switch (this.view) {
            case "top_alltime":
                this.getTopAllTime();
                break;
            case "top_month":
                this.getTopMonth();
                break;
        }
    }

    private getTopAllTime() {
        if (this.topAllTime) return;

        $.getJSON("/rest/coffee/ordersbyproduct")
            .then((orders: OrdersByProduct) => {
                 this.topAllTime = [];
                 for (let productId in orders) {
                     let product = this.PRODUCTS[productId];
                     if (product) {
                         this.topAllTime.push({
                             product: product,
                             count: orders[productId]
                         });
                     }
                 }
                 this.topAllTime.sort((a, b) => b.count - a.count);
                 this.$scope.$applyAsync();
             });
    }

    private getTopMonth() {
        if (this.topMonth) return;

        let start = Math.round(new Date().getTime() / 1000 - 30 * 24 * 3600);

        $.getJSON("/rest/coffee/ordersbyproduct", { from: start })
            .then((orders: OrdersByProduct) => {
                 this.topMonth = [];
                 for (let productId in orders) {
                     let product = this.PRODUCTS[productId];
                     if (product) {
                         this.topMonth.push({
                             product: product,
                             count: orders[productId]
                         });
                     }
                 }
                 this.topMonth.sort((a, b) => b.count - a.count);
                 this.$scope.$applyAsync();
             });
    }
}

export default {
    name: 'piCoffeeStats',
    config: {
        templateUrl: 'src/coffee/pi-coffee-stats.html',
        controller: CoffeeStatsController
    }
};
