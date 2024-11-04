import { SportPositions } from './SportPositions';
import { InjuryStatuses } from './InjuryStatuses';
import { CompetitorProviderIds } from './CompetitorProviderIds';
import { Teams } from './Teams';
import { CompetitorsEvents } from './CompetitorsEvents';
import { CompetitorStatuses } from './CompetitorStatuses';
import { Leagues } from './Leagues';
export interface Competitors {
    competitorId: number;
    dateOfBirth?: Date;
    heightInInches?: number;
    leagueId: number;
    teamId?: number;
    weightInPounds?: number;
    college?: string;
    competitorStatusCode?: string;
    firstName: string;
    highSchool?: string;
    injuryStatusId?: string;
    lastName: string;
    uniformIdentifier?: string;
    teams?: Teams;
    competitorStatuses?: CompetitorStatuses;
    injuryStatuses?: InjuryStatuses;
    leagues?: Leagues;
    competitorsEvents?: CompetitorsEvents[];
    sportPositions?: SportPositions[];
    competitorProviderIds?: CompetitorProviderIds;
}
