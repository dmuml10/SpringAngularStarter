// angular > core
import {OnInit} from "@angular/core";
// model > filter
import {AbstractFilter} from "../model/filter/abstract-filter";
// model > enum
import {LanguageType} from "../model/enum/language-type.enum";
import {StatusType} from "../model/enum/status-type.enum";
// util
import {MESSAGES} from "../util/messages";
import {AuthenticationUtils} from "../util/authentication-utils";
// rxjs
import {isNumeric} from "rxjs/util/isNumeric";

export abstract class AbstractComponent implements OnInit {

	// search filter constants

	protected readonly searchFilterClearButtonIcon: string = "fa-minus";

	protected readonly searchFilterSearchButtonIcon: string = "fa-search";

	// search table general constants

	protected readonly searchTableRows: number = 15;

	protected readonly searchTablePageLinks: number = 3;


	protected readonly confirmDialogIcon: string = "fa-question-circle";

	// enum lists

	protected readonly languageTypes: string[] = Object.keys(LanguageType).filter((key: string) => !isNumeric(key));

	protected readonly statusTypes: string[] = Object.keys(StatusType).filter((key: string) => !isNumeric(key));

	// table fields

	protected tableTotalRecords: number = 0;

	protected loading: boolean = true;

	protected constructor() {}

	public ngOnInit(): void {

	}

	// check user permission
	protected hasPermission(permission: string): boolean {
		return AuthenticationUtils.hasPermission(permission);
	}

	// get i18n message
	protected getMessage(key: string, ...params: any[]): string {
		// TODO get user language
		let value: string = MESSAGES[key]['en'];

		for (let i = 0; i < params.length; i++) {
			value = value.replace('{' + i + '}', params[i]);
		}

		return value;
	}

	// adds error message to the growl component
	protected showErrorMessage(message: string, ...params: any[]): void {

	}

	// handles component errors
	protected handleError(error: any): void {
		console.error(error);
	}

	// initializes abstract filter paging fields
	protected initPagingFilter(filter: AbstractFilter): void {
		filter.offset = 0;
		filter.numRows = this.searchTableRows;
	}

}