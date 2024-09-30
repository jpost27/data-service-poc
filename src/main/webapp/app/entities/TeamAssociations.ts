import { Teams } from './Teams';
import { TeamAssociationTypes } from './TeamAssociationTypes';
export interface TeamAssociations {
    teamAssociationId: number;
    name?: string;
    shortName?: string;
    teamAssociationTypeCode: string;
    teamAssociationTypes?: TeamAssociationTypes;
    teams?: Teams[];
}
