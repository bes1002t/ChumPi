interface OrdersByProduct {
    [product: string]: number;
}

interface ProductOrders {
    product: string;
    count: number;
}

class CoffeeStatsController {
    orders: ProductOrders[];

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
        $.getJSON("/rest/coffee/ordersbyproduct")
            .then((orders: OrdersByProduct) => this.update(orders));
    }

    private update(orders: OrdersByProduct) {
        this.orders = [];
        for (let productId in orders) {
            let product = this.PRODUCTS[productId];
            if (product) {
                this.orders.push({
                    product: product,
                    count: orders[productId] 
                });
            }
        }
        this.orders.sort((a, b) => b.count - a.count);
        this.$scope.$applyAsync();
    }
}

export default {
    name: 'piCoffeeStats',
    config: {
        templateUrl: 'src/coffee/pi-coffee-stats.html',
        controller: CoffeeStatsController
    }
};
