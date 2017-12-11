// angular > core
import {Component, OnInit} from "@angular/core";
// model > entity
import {Dummy} from "../../model/entity/dummy";
// component
import {AbstractComponent} from "../abstract-component";
// service
import {ConfirmationService} from "primeng/components/common/confirmationservice";
import {MessageService} from "primeng/components/common/messageservice";
import {DummyService} from "../../service/dummy.service";
import {User} from "../../model/entity/user";

@Component({
	selector: 'dummies',
	templateUrl: './dummy.component.html',
	styleUrls: ['./dummy.component.css']
})
export class DummiesComponent extends AbstractComponent implements OnInit {

	private entity: Dummy = new Dummy();

	private user: User;

	public constructor(private service: DummyService, confirmationService: ConfirmationService, messageService: MessageService) {
		super(confirmationService, messageService);
	}

	public ngOnInit(): void {
		super.ngOnInit();
	}

	private get(): void {
		this.service.get(1).subscribe((user: User) => {
			this.user = user;
		}, (error: any) => super.handleError(error));
	}

}