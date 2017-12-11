// model > entity
import {AbstractEntity} from "./abstract-entity";

export class User extends AbstractEntity {

	id: number;

	username: string;

	email: string;

	name: string;

	status: string;

	language: string;

}