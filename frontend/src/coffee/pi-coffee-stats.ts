interface OrdersByProduct {
    [product: string]: number;
}

interface OrdersByHour {
    [hourOfWeek: string]: number;
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

    days = [ "Mon", "Tue", "Wed", "Thu", "Fri" ];
    hours = [ 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18 ];
    ordersByHour: number[][];
    maxOrders: number;

    private PRODUCTS = {
        "5": "Coffee, Black",
        "6": "Coffee with Sugar",
        "7": "Coffee with Cream",
        "8": "Coffee with Sugar and Cream",
        "9": "Espresso",
        "10": "Cappucino Choco",
        "11": "Vanilla Milk",
        "12": "Hot Chocolate",
        "13": "Latte Macchiato",
        "14": "Malt Coffee"
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
            case "hourly":
                this.getOrdersByHour();
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

    private getOrdersByHour() {
        if (this.ordersByHour) return;

        $.getJSON("/rest/coffee/ordersbyhour")
            .then((orders: OrdersByHour) => {
                 this.ordersByHour = this.days.map(_day => {
                     let o: number[] = [];
                     for (let i = 0; i < 24; ++i) o.push(0);
                     return o;
                 });
                 this.maxOrders = 1;

                 for (let hourOfWeek in orders) {
                     let day = Math.floor(+hourOfWeek / 24);
                     let hour = +hourOfWeek % 24;
                     console.log(day, hour);
                     if (day > this.days.length) continue;
                     this.ordersByHour[day][hour] = orders[hourOfWeek];
                     this.maxOrders = Math.max(this.maxOrders, orders[hourOfWeek]);
                 }
                 console.log(this.ordersByHour);
                 this.$scope.$applyAsync();
             });

        /*
        for (let day = 0; day < this.days.length; ++day) {
            let dayOrders: number[] = [];
            for (let hour = 0; hour < this.hours.length; ++hour) {
                let orders = Math.random() * 1000;
                dayOrders.push(orders);
                this.maxOrders = Math.max(this.maxOrders, orders);
            }
            this.ordersByHour.push(dayOrders);
        }
        */
    }

}

export default {
    name: 'piCoffeeStats',
    config: {
        templateUrl: 'src/coffee/pi-coffee-stats.html',
        controller: CoffeeStatsController
    }
};
