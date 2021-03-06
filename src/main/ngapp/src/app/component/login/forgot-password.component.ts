import {Component} from "@angular/core";
import {Router} from "@angular/router";
import {AuthenticationService} from "../../service/authentication.service";

@Component({
	moduleId: module.id,
	templateUrl: 'forgot-password.component.html',
	styleUrls: ['forgot-password.component.css']
})

export class ForgotPasswordComponent {

	model: any = {};

	loading = false;

	error = '';

	constructor(private router: Router, private authenticationService: AuthenticationService) {}

	passwordRestorationLink() {
		this.loading = true;

		this.loading = false;
	}

}