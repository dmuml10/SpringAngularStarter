// angular > core
import {Component} from "@angular/core";
// angular > router
import {Router} from "@angular/router";
// component
import {AbstractComponent} from "./component/abstract-component";
// service
// util
import {AuthenticationUtils} from "./util/authentication-utils";
import {Utils} from "./util/utils";

@Component({
	selector: 'app',
	templateUrl: './app.component.html',
	styleUrls: ['./app.component.css']
})
export class AppComponent extends AbstractComponent {

	public constructor(private router: Router) {
		super();
	}

	private isLoggedIn(): boolean {
		return AuthenticationUtils.isLoggedIn();
	}

	private logout(): void {
		AuthenticationUtils.logout();

		this.router.navigate(['/login']).then((result: boolean) => {}, (error: any) => {
				Utils.handleError(error);
			}
		)
	}
}